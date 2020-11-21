import org.apache.kafka.clients.admin.*;

import java.util.*;
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
        client.createTopics(Collections.singleton(topic)).values().get(topic);
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
    public Collection<MemberDescription> listAllConsumerIDs(String groupID) throws ExecutionException, InterruptedException {
        ConsumerGroupDescription descr = null;
        List<String> groupIDs = Collections.singletonList(groupID);
        Map<String, ConsumerGroupDescription> groups = client.describeConsumerGroups(groupIDs).all().get();
        for (final String groupId : groupIDs) {
            descr = groups.get(groupId);
            //find if any description is connected to the topic with topicName
            System.out.println(descr);
        }
        return descr.members();
    }
    public boolean checkExistConsumerID(String groupID, String userID) throws ExecutionException, InterruptedException {
        return listAllConsumerIDs(groupID).contains(userID);
    }
}
