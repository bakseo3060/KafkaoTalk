public class MaxlengthException extends Exception{
    public MaxlengthException() {
        super("userID, chatroomName은 32를 초과할 수 없습니다.");
    }
}
class ExistRoomException extends Exception {
    public ExistRoomException() {
        super("존재하지 않는 채팅방입니다.");
    }
}