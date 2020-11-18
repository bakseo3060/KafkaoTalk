public class ChatroomWindow extends KafkaoTalk{
    String[] contents = new String[] {"Chatroom Window","Read", "Write", "Reset", "Exit"};
    public static void readMsg() {
        //TODO : 1. Read 선택 시 topic으로부터 메시지 읽고 콘솔에 출력
    }
    public static void writeMsg() {
        //TODO : 2. Write 선택 시 chat room에 메시지 작성
        //현재 chatroom에 대한 Topic에 기록(Record)
        //key : User ID, value : written text
    }
    public static void reset() {
        //TODO : 3. Reset 선택 시 consumer offset을 가장 처음으로 이동
    }
    public static void start() {

        while(true){
            switch (menu) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
