public class LoginWindow extends KafkaoTalk{
    String[] contents = new String[] {"Welcome to KafkaoTalk", "Log in", "Exit"};
    public static void createID() {
        //TODO : 1. Login 선택 시 user ID를 콘솔에 입력받아 저장한다. MAX_LENGTH = 32
        try {
            inputCommand("ID: "); //ID 입력받기
            if(userID.length() >= MAX_LENGTH) {
                throw new MaxlengthException();
            }
            currentWindow = 1; //TODO : ID생성 후 Chatting Window로 이동
        } catch(MaxlengthException e) {
                System.out.println(e);
        }

    }
    public static void start() {
        inputCommand("");
        switch (menu) {
            case 1:
                createID();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}

