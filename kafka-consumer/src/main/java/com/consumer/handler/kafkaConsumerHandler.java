package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consumer.redis.RedisCache;
import com.consumer.service.DataService;
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

@Component
@Slf4j
public class kafkaConsumerHandler {

    @Autowired
    DataService testService;

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

            if(!opList.contains(op)){
                log.info("insert Msg");
                opList.add(op);
                testService.insertMsg(jsonObject.getString("key"),op,jsonObject.toJSONString());
            }
        }catch (Exception e){
            //记录失败信息
            log.error(e.getMessage());
        }finally {
            // 手工签收机制
            consumer.commitSync(currentOffset);
        }
    }
}
