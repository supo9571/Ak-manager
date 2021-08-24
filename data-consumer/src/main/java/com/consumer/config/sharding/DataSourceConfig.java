package com.consumer.config.sharding;

import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author marvin 2021/8/18
 * 数据源配置和库策略、表策略
 */
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${spring.datasource.dataNodes}")
    private String dataNodes;

    @Value("${spring.datasource.sqlShow}")
    private String sqlShow;
    // 配置sharding-jdbc的DataSource，给上层应用使用，这个DataSource包含所有的逻辑库和逻辑表，应用增删改查时，修改对应sql
    // 然后选择合适的数据库继续操作。因此这个DataSource创建很重要。
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource shardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 订单表配置，可以累计添加多个配置
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());

        // 打印SQL
        Properties props = new Properties();
        props.put("sql.show", sqlShow);

        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()),
                new ConcurrentHashMap(), props);

    }

    // 创建data_coins 表规则
    @Bean
    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("data_coins");
        // 设置数据节点
        orderTableRuleConfig.setActualDataNodes(dataNodes);
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("mstime", TableRuleConfig.class.getName(), TableRuleConfig.class.getName()));
        return orderTableRuleConfig;
    }

    // 下面函数是获取数据源，即包含有多少个数据库，读入到系统中存放于map中
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("sys-data",createDataSource(jdbcUrl));
        return result;
    }

    private DataSource createDataSource(final String jdbcUrl) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(driverClassName);
        result.setUrl(jdbcUrl);
        result.setUsername(username);
        result.setPassword(password);
        result.setMaxActive(10);
        result.setMaxIdle(5);
        result.setMinIdle(3);
        result.setInitialSize(5);
        return result;
    }

    @Bean("sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(shardingDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        return sessionFactory.getObject();
    }

    /**
     * - 需要手动配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTmplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTmplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTmplate;
    }
}
