import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
public class Admin{

    private AdminClient client;

    public Admin(){
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        client = AdminClient.create(config);
    }

    public void createNewTopic(String roomName) {
        NewTopic topic = new NewTopic(roomName, 1, (short)1);
        System.out.println(roomName);
        client.createTopics(Collections.singleton(topic)).values().get(topic);
//        client.createTopics(Collections.singleton(topic)).values().get(topic);
    }
    public void listAllTooics() throws ExecutionException, InterruptedException {
        client.listTopics().names().get().forEach(System.out::println);
    }
}
