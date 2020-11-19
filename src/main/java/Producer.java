import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
    private KafkaProducer<String, String> producer;

    public void sendToTopic(String roomName, String userID, String text) {
        ProducerRecord<String, String> record = new ProducerRecord<>(roomName, userID, text);
        producer.send(record);
    }
    public void closeChatProducer() {
        producer.close();
    }
    public Producer() {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "id1");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        producer = new KafkaProducer<>(config);
    }
}
