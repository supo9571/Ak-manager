package com.consumer.sharding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * @author marvin 2021/8/18
 * 数据库分库策略
 */
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {

        String db_name = "db_";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(preciseShardingValue.getValue());
            String year = String.format("%tY", date);
            String mon = String.format("%tm", date);
            db_name = db_name + year + mon;
            System.out.println("db_name:" + db_name);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (String each : collection) {
            System.out.println("db:" + each);
            if (each.equals(db_name)) {
                return each;
            }
        }

        throw new IllegalArgumentException();
    }
}
