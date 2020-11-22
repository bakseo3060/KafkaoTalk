import java.util.concurrent.ExecutionException;

public class ChattingWindow extends KafkaoTalk{
    String[] contents = new String[] {"Chatting Window", "List", "Make", "Join", "Log out"};
    static String command = "";

    public static void listChatroom () throws ExecutionException, InterruptedException {
        //TODO : 1. List 선택 시 현재 계정이 생성한 모든 chatroom 출력
//        chatAdmin.listAllTopics().forEach(System.out::println);
        chatConsumer.listConsumerTopics();
    }
    public static void createNewChatroom() {
        //TODO : 2. Make 선택 시 새로운 chat room 생성
        //1. roomName을 콘솔로부터 입력받는다.
        //2. roomName을 이름으로 갖는 topic을 생성한다.
        //3. 방을 생성한다는 것은 현재 컨슈머가 그 방(topic)을 구독한다는 것으로 본다.

        try {
            command = "Chat room name: ";
            inputCommand(command);
            if(roomName.length() >= MAX_LENGTH) {
                throw new MaxlengthException();
            }

            //TODO : userID가 생성하려는 roomName의 중복 여부를 체크 후 topic에 추가
            if(chatConsumer.checkExistRecord(roomName) == false){
                chatProducer.sendToTopic(userID+"_chatroomInfo", roomName,"");
                if(chatAdmin.checkExistTopic(roomName) == false) {//다른 유저가 생성하지 않은 신규 채팅방인 경우 생성 메시지 출력
                    System.out.println("\"" + roomName + "\"" + " is created!");
                }
                chatAdmin.createNewTopic(roomName);
            }
            else {
                System.out.println("\""+roomName+"\""+" already exist!");
            }
        } catch(MaxlengthException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
        chatConsumer.closeChatConsumer();
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

