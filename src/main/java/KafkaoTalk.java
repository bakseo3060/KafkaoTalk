import java.util.Scanner;

public class KafkaoTalk {
    static int currentWindow = 0;
    static int menu = 0;
    static Scanner sc = new Scanner(System.in);

    public static void printMenu(String[] contents) {
        System.out.println(contents[0]);
        for(int i=1; i< contents.length; i++) {
            System.out.println(i + ". "+contents[i]);
        }
    }
    public static void inputCommand() {
        //TODO : menu 선택하여 함수 호출
        System.out.println("kafkaotalk> ");
        menu = sc.nextInt();
    }

    public static void main(String[] args) {

        int menu = 0; //0~4, 각 클래스 마다 작동 방식 다름
        //Window 초기화
        LoginWindow login = new LoginWindow();
        ChattingWindow chatting = new ChattingWindow();
        ChatroomWindow chatroom = new ChatroomWindow();


        //TODO: STDIN으로 menu 받아서 기능 선택
        while(true) {
            //TODO : 각 Window 객체 띄우기
            switch (currentWindow) {
                case 0:
                    printMenu(login.contents);
                    login.start();
                    break;
                case 1:
                    printMenu(chatting.contents);
                    chatting.start();
                    break;
                case 2:
                    printMenu(chatroom.contents);
                    chatroom.start();
                    break;
                default:
                    break;
            }
        }
    }
}