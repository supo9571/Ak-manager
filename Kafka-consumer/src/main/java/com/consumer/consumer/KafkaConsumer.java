package com.consumer.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consumer.enums.OpEnum;
import com.consumer.handler.InsertHandler;
import com.consumer.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/17
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private InsertHandler insertHandler;

    private static List opList = new ArrayList();

//    @KafkaListener(groupId = "group003", topics = "bills_log")
    public void onMessage(ConsumerRecord<String, Object> record,
                          Consumer<?, ?> consumer,
                          Acknowledgment ack) {
        Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();
        try {
            JSONObject jsonObject = JSON.parseObject(record.value().toString());
            //设置偏移量
            currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                    new OffsetAndMetadata(record.offset() + 5));
            //存库
            String op = jsonObject.getString("op");

            if(OpEnum.REGISTER.equals(op)){
                insertHandler.insertRegister(jsonObject);
            }
            if(OpEnum.ADDCOINS.equals(op)){
                insertHandler.insertAddcoins(jsonObject);
            }
            if(OpEnum.REDUCECOINS.equals(op)){
                insertHandler.insertAddcoins(jsonObject);
            }
        }catch (Exception e){
            //记录失败信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offset",record.offset());
            jsonObject.put("errMsg",e.getMessage());
            jsonObject.put("value",record.value());
            List errMsgs = new ArrayList();
            errMsgs.add(jsonObject.toJSONString());
            log.error(e.getMessage());
            redisCache.setCacheList("kafka_error", errMsgs);
        }finally {
            // 手工签收机制
            consumer.commitSync(currentOffset);
        }
    }
}
