#Welcome to my KafkaoTalk!
##This is text-based chatting program with kafka and java
####1. move to your kafka folder
    cd kafka_2.13-2.6.0 
####2. Activate Kafka server and Zookeeper
    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties
####3. Open project and Run KafkaoTalk
    Intellij 상에서 본 프로젝트의 KafkaoTalk을 실행합니다.

####4. How to use 
    본 프로그램은 아래의 3가지 window로 구성되어 있습니다.
    각 메뉴에 해당하는 숫자를 콘솔에 입력합니다. 
#####4.a. Login Window
    1. Log in
        접속할 ID를 입력합니다. 
    2. Exit
        프로그램을 종료합니다.
    3. (추가구현)Delete All Chat Room
        프로그램에 의해 생성된 채팅방을 모두 삭제합니다.
#####4.b. Chatting Window
    1. List
        현재 접속한 ID가 생성한 채팅방을 출력합니다.
        ex) kafkaotalk> ID: joey
            
            joey's Chatting
            1. List
            2. Make
            3. Join
            4. Log out
            
            kafkaotalk> 1
            sogang
            kafka
            kafkaotalk> 
    2. Make
        콘솔로 채팅방 이름을 입력받아 현재 ID의 채팅방 목록에 추가할 채팅방을 생성합니다.
        신규 채팅방인 경우 "roomName" is created 메시지가 출력됩니다.
            ex) joey's Chatting
                1. List
                2. Make
                3. Join
                4. Log out
                
                kafkaotalk> 1
                kafkaotalk> 2
                kafkaotalk> Chat room name: sogang
                "sogang" is created!
        해당 ID가 roomName의 채팅방 목록을 갖고 있는 경우 already exist! 메시지가 출력됩니다.
            ex) joey's Chatting
                1. List
                2. Make
                3. Join
                4. Log out
                
                kafkaotalk> 1
                sogang
                kafka
                kafkaotalk> 2
                kafkaotalk> Chat room name: sogang
                "sogang" already exist!
        다른 유저가 이미 생성한 채팅방을 create하는 경우 아무런 메시지가 출력되지 않고, 현재 ID의 채팅방 목록에 추가됩니다. 
            ex) seojoon's Chatting
                1. List
                2. Make
                3. Join
                4. Log out
                
                kafkaotalk> 1
                kafkaotalk> 2
                kafkaotalk> Chat room name: sogang
                kafkaotalk> 1
                sogang
    3. Join
        콘솔로 채팅방 이름을 입력받아 해당 채팅방에 참여합니다.
        성공적으로 채팅방에 참여하는 경우 채팅방 이름과 함께 Chatroom Window의 메뉴가 출력됩니다.
            ex) kafkaotalk> Chat room name: sogang
                
                sogang
                1. Read
                2. Write
                3. Reset
                4. Exit
                
                kafkaotalk> 
        현재 ID의 채팅방 목록에 존재하지 않는 채팅방에 참여시 에러 메시지를 출력합니다.
            ex) joey's Chatting
                1. List
                2. Make
                3. Join
                4. Log out
                
                kafkaotalk> 1
                sogang
                kafka
                kafkaotalk> 3
                kafkaotalk> Chat room name: not exist
                ExistRoomException: 존재하지 않는 채팅방입니다.
                kafkaotalk> 
    4. Log out
        로그아웃이 되며 Login Window로 돌아옵니다.
#####4.c. Chatroom Window
    1. Read
        현재 참여한 채팅방에서 읽지않은 메시지부터 가장 최근 메시지까지 출력합니다.
        ex) sogang
            1. Read
            2. Write
            3. Reset
            4. Exit
            
            kafkaotalk> 2
            kafkaotalk> Text: Hey, Seojoon
            kafkaotalk> 2
            kafkaotalk> Text: You need to sleep!
            kafkaotalk> 1
            joey : Hey, Seojoon
            joey : You need to sleep!
    2. Write
        현재 참여한 채팅방에 콘솔창에 메시지를 작성하여 입력합니다.
        ex) sogang
            1. Read
            2. Write
            3. Reset
            4. Exit
            
            kafkaotalk> 2
            kafkaotalk> Text: Hey, Seojoon
            kafkaotalk> 2
            kafkaotalk> Text: You need to sleep!
    3. Reset
        현재 참여한 채팅방에서 메시지를 처음부터 읽기 위해 Topic의 offset을 처음으로 되돌립니다.
        Reset이후 다시 읽을 경우 처음부터 가장 최근 메시지까지 출력됩니다.
        ex) sogang
            1. Read
            2. Write
            3. Reset
            4. Exit
            
            kafkaotalk> 2
            kafkaotalk> Text: Hey, Seojoon
            kafkaotalk> 2
            kafkaotalk> Text: You need to sleep!
            kafkaotalk> 1
            joey : Hey, Seojoon
            joey : You need to sleep!
            kafkaotalk> 3
            kafkaotalk> 1
            joey : Hey, Seojoon
            joey : You need to sleep!
    4. Exit
        Chatting Window로 돌아옵니다.

####5. Edge Case
#####5.a. User A가 채팅방 K의 메시지를 읽거나 썼을 때, User B가 참여한 채팅방 K의 메시지에 지장이 없는가?
#####5.b. User A가 채팅방 K의 메시지를 읽거나 썼을 때, User A의 채팅방 L의 메시지에 지장이 없는가? \
    joey의 채팅방인 sogang, kafka가 독립적으로 작동하는지 테스트해보도록 하자.
    
        joey's Chatting
        1. List
        2. Make
        3. Join
        4. Log out
        
        kafkaotalk> 1
        sogang
        kafka
        kafkaotalk> 3
        kafkaotalk> Chat room name: kafka
        
        kafka
        1. Read
        2. Write
        3. Reset
        4. Exit
        
        kafkaotalk> 2
        kafkaotalk> Text: test1
        kafkaotalk> 2
        kafkaotalk> Text: test2
        kafkaotalk> 1
        joey : test1
        joey : test2
        kafkaotalk> 4
    
    joey의 kafka에 test1, test2를 작성하고 읽기를 작성하였다.
    
    이후 joey의 sogang에 작성했던 메시지들에 동일하게 유지되어 있는지 확인한다.
        joey's Chatting
        1. List
        2. Make
        3. Join
        4. Log out
        
        kafkaotalk> 1
        sogang
        kafka
        kafkaotalk> 3
        kafkaotalk> Chat room name: sogang
        
        sogang
        1. Read
        2. Write
        3. Reset
        4. Exit
        
        kafkaotalk> 1
        kafkaotalk> 3
        kafkaotalk> 1
        joey : Hey, Seojoon
        joey : You need to sleep!
        
    기존에 sogang에 작성했던 메시지들이 동일하게 작동한 것을 볼 수 있다.
    
#####5.c. Logout 이후에도 동일하게 채팅방과 메시지 정보가 유지되는가?
    위에서 이미 읽었던 메시지들의 offset이 그대로 유지되는지 테스트한다.
    
        joey's Chatting
        1. List
        2. Make
        3. Join
        4. Log out
        
        kafkaotalk> 4
        
        Welcome to KafkaoTalk
        1. Log in
        2. Exit
        3. Delete All Chat Room
        
        kafkaotalk> 1
        kafkaotalk> ID: joey
        
        joey's Chatting
        1. List
        2. Make
        3. Join
        4. Log out
        
        kafkaotalk> 1
        sogang
        kafka
        kafkaotalk> 3
        kafkaotalk> Chat room name: sogang
        
        sogang
        1. Read
        2. Write
        3. Reset
        4. Exit
        
        kafkaotalk> 1
        kafkaotalk> 3
        kafkaotalk> 1
        joey : Hey, Seojoon
        joey : You need to sleep!
        kafkaotalk> 4
        
        joey's Chatting
        1. List
        2. Make
        3. Join
        4. Log out
        
        kafkaotalk> 3
        kafkaotalk> Chat room name: kafka
        
        kafka
        1. Read
        2. Write
        3. Reset
        4. Exit
        
        kafkaotalk> 1
        kafkaotalk> 3
        kafkaotalk> 1
        joey : test1
        joey : test2
    
    이미 읽었던 메시지므로 logout 후 재접속 하여도 읽기 수행시 아무런 메시지가 출력이 되지 않음을 볼 수 있다.
    즉, offset이 동일하게 유지됨을 볼 수 있다.
    또한 Reset 수행시 메시지가 정상 출력됨을 확인할 수 있다.
    
#####5.d.    

