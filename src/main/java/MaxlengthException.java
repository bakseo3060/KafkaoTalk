public class MaxlengthException extends Exception{
    public MaxlengthException() {
        super("userID, chatroomName은 32를 초과할 수 없습니다.");
    }
}