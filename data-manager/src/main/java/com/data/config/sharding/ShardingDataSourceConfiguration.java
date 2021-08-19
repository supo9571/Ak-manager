package com.data.config.sharding;

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
public class ShardingDataSourceConfiguration {
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${shardingjdbc.sql.show}")
    private String sqlShow;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${spring.datasource.dataNodes}")
    private String dataNodes;

    // 配置sharding-jdbc的DataSource，给上层应用使用，这个DataSource包含所有的逻辑库和逻辑表，应用增删改查时，修改对应sql
    // 然后选择合适的数据库继续操作。因此这个DataSource创建很重要。
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource shardingDataSource() throws SQLException {

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 订单表配置，可以累计添加多个配置
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        // shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());

        // 打印SQL
        Properties props = new Properties();
        props.put("sql.show", sqlShow);

        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()),
                new ConcurrentHashMap<String, Object>(), props);

    }

    // 创建data_coins 表规则
    @Bean
    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();

        orderTableRuleConfig.setLogicTable("data_coins");
        orderTableRuleConfig.setLogicIndex("mstime");

        // 设置数据库策略，传入的是sys_time
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
//                new StandardShardingStrategyConfiguration("mstime", DatabaseShardingAlgorithm.class.getName()));
        // 设置数据表策略，传入的是sys_time
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("mstime", TableShardingAlgorithm.class.getName()));

        // 设置数据节点，格式为dbxx.tablexx。这里的名称要和map的别名一致。下面两种方式都可以
        // orderTableRuleConfig.setActualDataNodes("db_${0..1}.t_order_${0..1}");
        orderTableRuleConfig.setActualDataNodes(dataNodes);
        // 设置纵列名称
        // orderTableRuleConfig.setKeyGeneratorColumnName("ID");
        return orderTableRuleConfig;
    }

    // 下面函数是获取数据源，即包含有多少个数据库，读入到系统中存放于map中
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("sys-data",createDataSource(jdbcUrl));
        return result;
    }

    private DataSource createDataSource(final String jdbcUrl) {
        // 使用默认连接池
        BasicDataSource result = new BasicDataSource();
        // 指定driver的类名，默认从jdbc url中自动探测
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        // 设置数据库路径
        result.setUrl(jdbcUrl);
        // 设置数据库用户名
        result.setUsername(username);
        // 设置数据密码
        result.setPassword(password);
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
