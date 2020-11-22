import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class LoginWindow extends KafkaoTalk{
    String[] contents = new String[] {"Welcome to KafkaoTalk", "Log in", "Exit", "Delete All Chat Room"};
//    public static boolean checkExistID(String userID) throws ExecutionException, InterruptedException {
//        //TODO : 새로 입력받은 userID가 존재하는지 파
//        return chatAdmin.checkExistConsumerID("group_1", userID);
//    }
    public static void loginID(){

        //새로운 userID를 clientID로 갖는 consumer 생성.
        chatConsumer = new Consumer(userID);
        chatProducer = new Producer(userID);
        chatAdmin.createNewTopic(userID+"_chatroomInfo");
        chatConsumer.userChatroomConsumer.subscribe(Collections.singletonList(userID+"_chatroomInfo"));
    }
    public static void createID() {
        //TODO : 1. Login 선택 시 user ID를 콘솔에 입력받아 저장한다. MAX_LENGTH = 32
        try {
            inputCommand("ID: "); //ID 입력받기
            if(userID.length() >= MAX_LENGTH) {
                throw new MaxlengthException();
            }
            //userID clientID로 갖는 consumer 생성.

            currentWindow = 1; //TODO : ID 생성 후 Chatting Window 로 이동
        } catch(MaxlengthException e) {
                System.out.println(e);
        }

    }
    public static void deleteAllChatRoom() throws ExecutionException, InterruptedException {
        //TODO : 현재 생성되어있는 채팅방 모두 삭제
        chatAdmin.deleteKafkaTopics(chatAdmin.listAllTopics());
    }
    public static void start() throws ExecutionException, InterruptedException {

        while(currentWindow == 0) {
            inputCommand("");
            switch (menu) {
                case 1:
                    createID();
                    loginID();
                    break;
                case 2:
                    finishKafkaoTalk();
                    break;
                case 3:
                    deleteAllChatRoom();
                    break;
                default:
                    break;
            }
        }

    }
}

