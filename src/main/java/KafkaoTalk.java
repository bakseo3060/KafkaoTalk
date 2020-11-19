import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class KafkaoTalk {
    static int MAX_LENGTH = 32;
    static int currentWindow = 0;
    static int menu;
    static String userID;
    static String roomName;
    static String text;
    static Scanner sc = new Scanner(System.in);
    static Admin chatAdmin;
    static Consumer chatConsumer;
    static Producer chatProducer;

    static String prevArg;

    public static void printMenu(String[] contents) {
        System.out.println("\n"+contents[0]);
        for(int i=1; i< contents.length; i++) {
            System.out.println(i + ". "+contents[i]);
        }
        System.out.println();
    }
    public static void inputCommand(String args) {
        //TODO : menu 선택하여 함수 호출

        System.out.print("kafkaotalk> "+args);
        if (args.equals("")) {
            menu = sc.nextInt();
            prevArg = args;
        } else {
            if(prevArg.equals("")) {
                sc.nextLine(); //버퍼 비우기
            }
            if(args.equals("ID: ")) {
                userID = sc.nextLine();
            } else if(args.equals("Chat room name: ")) {
                roomName = sc.nextLine();
            } else if(args.equals("Text: ")) {
                text = sc.nextLine();
            }
        }

    }
    public static void finishKafkaoTalk() {
        System.out.println("THANK YOU FOR USING MY PROGRAM!");
        System.exit(0);
        chatConsumer.closeChatConsumer();
        chatProducer.closeChatProducer();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int menu = 0; //0~4, 각 클래스 마다 작동 방식 다름
        //Window 초기화
        LoginWindow login = new LoginWindow();
        ChattingWindow chatting = new ChattingWindow();
        ChatroomWindow chatroom = new ChatroomWindow();
        chatAdmin = new Admin();
        chatConsumer = new Consumer();
        chatProducer = new Producer();

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