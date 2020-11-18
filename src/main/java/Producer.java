import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "id1");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");

        KafkaProducer<String, String> producer = new KafkaProducer<>(config);
        ProducerRecord<String, String> record = new ProducerRecord<>("test2", "11", "Sogang");

        producer.send(record);
        producer.close();
    }
}
