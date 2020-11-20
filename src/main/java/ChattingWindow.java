import java.util.concurrent.ExecutionException;

public class ChattingWindow extends KafkaoTalk{
    String[] contents = new String[] {"Chatting Window", "List", "Make", "Join", "Log out"};
    static String command = "";

    public static void listChatroom () throws ExecutionException, InterruptedException {
        //TODO : 1. List 선택 시 모든 chatroom 출력
        chatAdmin.listAllTopics().forEach(System.out::println);;
    }
    public static void createNewChatroom() {
        //TODO : 2. Make 선택 시 새로운 chat room 생성
        //1. roomName을 콘솔로부터 입력받는다.
        //2. roomName을 이름으로 갖는 topic을 생성한다.
        try {
            command = "Chat room name: ";
            inputCommand(command);
            if(roomName.length() >= MAX_LENGTH) {
                throw new MaxlengthException();
            }
            chatAdmin.createNewTopic(roomName);
        } catch(MaxlengthException e) {
            System.out.println(e);
        }

    }
    public static void joinChatroom() {
        //TODO : 3. Join 선택 시 roomName을 갖는 방에 참여. MAX_LENGTH = 32
        //Chat Room Window로 이동
        //Error case : 생성되지 않은 방에 참여 or MAX_LENGTH 초과
        try {
            command = "Chat room name: ";
            inputCommand(command);
            if(roomName.length() >= MAX_LENGTH) {
                throw new MaxlengthException();
            }
            if(chatAdmin.checkExistTopic(roomName)) {
                currentWindow = 2;
                //roomName을 Consumer에서 subscribe
                chatConsumer.subscribeChatroom(roomName);
            } else {
                currentWindow = 1;
                throw new ExistRoomException();
            }
        } catch(MaxlengthException | ExistRoomException e) {
            System.out.println(e);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
    public static void logOut() {
        //TODO : 4. Logout 선택 시 Login Window로 이동
        currentWindow = 0;
    }

    public static void start() throws ExecutionException, InterruptedException {

        while(currentWindow == 1){
            inputCommand("");
            switch (menu) {
                case 1:
                    listChatroom();
                    break;
                case 2:
                    createNewChatroom();
                    break;
                case 3:
                    joinChatroom();
                    break;
                case 4:
                    logOut();
                    break;
                default:
                    break;
            }
        }
    }
}

