import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class Consumer {
    private KafkaConsumer<String, String> consumer;

    public void subscribeChatroom(String roomName) {
        //TODO: roomName을 갖는 채팅방을 구독한다.
        consumer.subscribe(Collections.singletonList(roomName));
    }
    public String getTextFromTopic() {
        //TODO: 현재 topic에서 가장 최신 text를 pop하여 출력한다.
        String msg = "";
        ConsumerRecords<String, String> records = consumer.poll(1000);
        for(ConsumerRecord<String, String> record : records) {
            msg = record.key()+" : "+record.value();
        }
        return msg;
    }
    public void resetCounsumerOffset(String roomName) {
        //TODO: 현재 Consummer의 Offset을 초기화 시킨다.
        Set<TopicPartition> topicPartitionSet = Collections.singleton(new TopicPartition(roomName, 0));
        consumer.seekToBeginning(topicPartitionSet);
    }
    public void closeChatConsumer() {
        consumer.close();
    }
    public Consumer() {
        Properties config = new Properties();
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "fo2s234dfsdf");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        consumer = new KafkaConsumer<>(config);
//        consumer.subscribe(Collections.singletonList("test2"));

//        try {
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(1000);
//                for (ConsumerRecord<String, String> record : records)
//                {
//                    System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n",
//                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
//                }
//            }
//        } finally {
//            consumer.close();
//        }

    }
}
