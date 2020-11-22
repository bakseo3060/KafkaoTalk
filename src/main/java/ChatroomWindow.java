public class ChatroomWindow extends KafkaoTalk{
    static String[] contents = new String[] {roomName,"Read", "Write", "Reset", "Exit"};

    public static void readMsg() {
        //TODO : 1. Read 선택 시 topic으로부터 메시지 읽고 콘솔에 출력
        chatConsumer.getTextFromTopic();
    }
    public static void writeMsg() {
        //TODO : 2. Write 선택 시 chat room에 메시지 작성
        //현재 chatroom에 대한 Topic에 기록(Record)
        //key : User ID, value : written text
        inputCommand("Text: ");
        chatProducer.sendToTopic(roomName, userID, text);
    }
    public static void reset() {
        //TODO : 3. Reset 선택 시 consumer offset을 가장 처음으로 이동
        chatConsumer.resetCounsumerOffset(roomName);
    }
    public static void start() {
        //현재 userID를 갖는 consumer를 chatConsumer로 지정.
        while(currentWindow == 2){
            inputCommand("");
            switch (menu) {
                case 1:
                    readMsg();
                    break;
                case 2:
                    writeMsg();
                    break;
                case 3:
                    reset();
                    break;
                case 4:
                    currentWindow = 1; //Exit하고 Chatting Window로 변경
                    break;
                default:
                    break;
            }
        }
    }
}
