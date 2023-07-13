package com.kafka.example.strconsumer.listeners;

import com.kafka.example.strconsumer.custom.StrConsumerCustomListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {


    //example with annotation for @KafkaListener
    @StrConsumerCustomListener(g = "group-1")
    public void update(String message) {
        log.info("UPDATE ::: Receive message: {}", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = {
                    @TopicPartition(topic = "str-topic", partitions = {"0"}),
            }, containerFactory = "strContainerFactory")
    public void create(String message) {
        log.info("CREATE ::: Receive message: {}", message);
        throw new IllegalArgumentException("EXCEPTION");
    }


    @KafkaListener(groupId = "group-1",
            topicPartitions = {
                    @TopicPartition(topic = "str-topic", partitions = {"1"}),
            }, containerFactory = "strContainerFactory")
    public void log(String message) {
        log.info("LOG ::: Receive message: {}", message);
    }

    //    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory") its same as below, consume all partitions kafka
    @KafkaListener(groupId = "group-2",
            topicPartitions = {
                    @TopicPartition(topic = "str-topic", partitions = {"0", "1"}),
            }, containerFactory = "strContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Receive message: {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void historyValid(String message) {
        log.info("HISTORY Valid ::: Receive message: {}", message);
    }
}
