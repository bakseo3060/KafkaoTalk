public class ChattingWindow extends KafkaoTalk{
    String[] contents = new String[] {"Chatting Window", "List", "Make", "Join", "Log out"};
    public static void listChatroom (){
        //TODO : 1. List 선택 시 모든 chatroom 출력
    }
    public static void createNewChatroom() {
        //TODO : 2. Make 선택 시 새로운 chat room 생성
        //chatroom을 topic으로 생성한다. 해당 topic은 key, value로 구성되어 있다
        //key : userID, value : written text
        //Producer를 활용한다.
    }
    public static void joinChatroom(String roomName) {
        //TODO : 3. Join 선택 시 roomName을 갖는 방에 참여. MAX_LENGTH = 32
        //Chat Room Window로 이동
        //Error case : 생성되지 않은 방에 참여 or MAX_LENGTH 초과
        currentWindow = 2;
    }
    public static void logOut() {
        //TODO : 4. Logout 선택 시 Login Window로 이동
        currentWindow = 0;
    }

    public static void start() {

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
                    joinChatroom(roomName);
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

