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

    @Autowired
    RedisCache redisCache;

    @KafkaListener(groupId = "group01", topics = "bills_log")
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
            //在线玩家人数
            if("online_player".equals(op)){
                testService.updateUserOnline(jsonObject.getString("online_count"),jsonObject.getString("time"));
            }else {
                testService.insertMsg(jsonObject.getString("key"),op,jsonObject.toJSONString());
            }

        }catch (Exception e){
            //记录失败信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offset",record.offset());
            jsonObject.put("errMsg",e.getMessage());
            jsonObject.put("value",record.value());
            List errMsgs = new ArrayList();
            errMsgs.add(jsonObject);
            log.error(e.getMessage());
            redisCache.setCacheList("kafka_error", errMsgs);
        }finally {
            // 手工签收机制
            consumer.commitSync(currentOffset);
        }
    }
}
