public class LoginWindow extends KafkaoTalk{
    String[] contents = new String[] {"Welcome to KafkaoTalk", "Log in", "Exit"};
    public static void createID() {
        //TODO : 1. Login 선택 시 user ID를 콘솔에 입력받아 저장한다. MAX_LENGTH = 32
        //Error case : MAX_LENGTH 초과
    }
    public static void start() {
        inputCommand();
        while(true){
            switch (menu) {
                case 1:
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

