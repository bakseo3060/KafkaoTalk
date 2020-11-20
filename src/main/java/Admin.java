import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
    public Set<String> listAllTopics() throws ExecutionException, InterruptedException {
        return client.listTopics().names().get();
    }
    public boolean checkExistTopic(String roomName) throws ExecutionException, InterruptedException {
        boolean topicExists  = client.listTopics().names().get().contains(roomName);
        return topicExists;
    }
    public void deleteKafkaTopics(Set<String> kafkaTopics) {

        DeleteTopicsResult deleteTopicsResult = client.deleteTopics(kafkaTopics);

        while (!deleteTopicsResult.all().isDone()) {
        }
    }
}
