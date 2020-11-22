import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.internals.KafkaConsumerMetrics;
import org.apache.kafka.clients.consumer.internals.SubscriptionState;
import org.apache.kafka.common.TopicPartition;

public class Consumer extends KafkaoTalk{
    private KafkaConsumer<String, String> consumer;
    public KafkaConsumer<String, String> userChatroomConsumer;
    public void listConsumerTopics() {
        String msg = null;
        ConsumerRecords<String, String> records = userChatroomConsumer.poll(500);
        for(ConsumerRecord<String, String> record : records) {
            msg = record.key();
            System.out.println(msg);
        }

        //offset 되돌리기
        Set<TopicPartition> topicPartitionSet = Collections.singleton(new TopicPartition(userID+"_chatroomInfo",0));

        userChatroomConsumer.seekToBeginning(topicPartitionSet);

    }
    public void subscribeChatroom(String roomName) {
        //TODO: roomName을 갖는 채팅방을 구독한다.
        consumer.subscribe(Collections.singletonList(roomName));
    }
    public boolean checkExistRecord(String roomName) {
        //TODO: 현재 topic에 roomName이 저장되어 있는지 체크한다.

        ConsumerRecords<String, String> records = userChatroomConsumer.poll(500);
        boolean ret = false;
        for(ConsumerRecord<String, String> record : records) {
            if(roomName.equals(record.key())) {
                ret = true;
                break;
            }
        }
        Set<TopicPartition> topicPartitionSet = Collections.singleton(new TopicPartition(userID+"_chatroomInfo",0));
        userChatroomConsumer.poll(500);
        userChatroomConsumer.seekToBeginning(topicPartitionSet);
        return ret;

    }
    public String getTextFromTopic() {
        //TODO: 현재 topic에서 가장 최신 text를 pop하여 출력한다.
        String msg = "";
        ConsumerRecords<String, String> records = consumer.poll(500);
        for(ConsumerRecord<String, String> record : records) {
            msg = record.key()+" : "+record.value();
            System.out.println(msg);
        }
        consumer.commitSync();
        return msg;
    }
    public void resetCounsumerOffset(String roomName) {
        //TODO: 현재 Consummer의 Offset을 초기화 시킨다.
        Set<TopicPartition> topicPartitionSet = Collections.singleton(new TopicPartition(roomName, 0));
//        consumer.poll(500); //seek하기 전 dummy call 필
        consumer.seekToBeginning(topicPartitionSet);
    }
    public void closeChatConsumer() {
//        userChatroomConsumer.commitSync();
//        consumer.commitSync();
        userChatroomConsumer.close();
        consumer.close();
    }
    public Consumer(String userID) {
        Properties config = new Properties();
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_1_"+userID);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,userID);

        //consumerHashMap에 이미 userID가 존재하는 경우 해당 consumer를 사용 .
        //그렇지 않을 경우 새로 생성하여 consumerHashMap에 추가.

        consumer = new KafkaConsumer<>(config);

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_2"+userID+"_chatroomInfo");
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,userID+"_chatroomInfo");
//        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        userChatroomConsumer = new KafkaConsumer<>(config);
    }
}
