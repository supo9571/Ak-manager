package com.data.config.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Date;

/**
 * @author marvin 2021/8/18
 * 数据表分表策略
 */
@Slf4j
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        String tableName = preciseShardingValue.getLogicTableName() + "_";
        try {
            Long time = preciseShardingValue.getValue();
            Date date  = new Date(time*1000);
            String year = String.format("%ty", date);
            String mon = String.format("%tm", date);
            tableName = tableName + year + mon;
            log.info(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String each : collection) {
            if (each.equals(tableName)) {
                return each;
            }
        }

        throw new IllegalArgumentException();

    }

}
