
public class UserManager {
    private static UserManager instance;
    public static UserManager getInstance() {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    private HashMap<Integer, User> usersById;
    private HashMap<String, User> usersByAccountName;
    private HashMap<Integer, User> onlineUsers;

    public void addUser(User fromUser, String toAccountName) {}
    public void approveAddRequest(AddRequest req) {}
    public void rejectAddRequest(AddRequest req) {}
    public void userSignedOn(String accountName) {}
    public void userSingedOff(String accountName) {}
}
public class User {
    private int id;
    private UserStatus status = null;
    private HashMap<Integer, PrivateChat> privateChats = new HashMap<Integer, PrivateChat>();
    private ArrayList<GroupChat> groupChats = new ArrayList<GroupChat>();
    private HashMap<Integer, AddRequest> receivedAddRequests = new HashMap<Integer, AddRequest>();
    private HashMap<Integer, AddRequest> sentAddRequests = new HashMap<Integer, AddRequest>();
    
    private HashMap<Integer, User> contacts = new HashMap<Integer, User>();
    private String accountName;
    private String fullName;
    
    public User(int id, String accountName, String fullName) {
        this.accountName = accountName;
        this.fullName = fullName;
        this.id = id;
    }
    
    public boolean sendMessageToUser(User toUser, String content) {
        PrivateChat chat = privateChats.get(toUser.getId());
        if (chat == null) {
            chat = new PrivateChat(this, toUser);
            privateChats.put(toUser.getId(), chat);
        }
        Message message = new Message(content, new Date());
        return chat.addMessage(message);
    }
    
    public boolean sendMessageToGroupChat(int groupId, String content) {
        GroupChat chat = groupChats.get(groupId);
        if (chat != null) {
            Message message = new Message(content, new Date());
            return chat.addMessage(message);
        }
        return false;
    }
    
    public boolean addContact(User user) {
        if (contacts.containsKey(user.getId())) {
            return false;
        } else {
            contacts.put(user.getId(), user);
            return true;
        }
    }
    
    public void receivedAddRequest(AddRequest req) {
        int senderId = req.getFromUser().getId();
        if (!receivedAddRequests.containsKey(senderId)) {
            receivedAddRequests.put(senderId, req);
        }       
    }
    
    public void sentAddRequest(AddRequest req) {
        int receiverId = req.getFromUser().getId();
        if (!sentAddRequests.containsKey(receiverId)) {
            sentAddRequests.put(receiverId, req);
        }       
    }
    
    public void removeAddRequest(AddRequest req) {
        if (req.getToUser() == this) {
            receivedAddRequests.remove(req);
        } else if (req.getFromUser() == this) {
            sentAddRequests.remove(req);
        }
    }
    
    public void requestAddUser(String accountName) {
        UserManager.getInstance().addUser(this, accountName);
    }
    
    public void addConversation(PrivateChat conversation) {
        User otherUser = conversation.getOtherParticipant(this);
        privateChats.put(otherUser.getId(), conversation);
    }

    public void addConversation(GroupChat conversation) {
        groupChats.add(conversation);
    }   
}
public class UserStatus {
    private String message;
     private UserStatusType type;
     public UserStatus(UserStatusType type, String message) {
         this.type = type;
         this.message = message;
     }
}
public enum UserStatusType {
    Offline, Away, Idle, Available, Busy
}

public enum RequestStatus {
    Unread, Read, Accepted, Rejected
}
public class AddRequest {
    private User fromUser;
    private User toUser;
    private Date date;
    RequestStatus status;
    
    public AddRequest(User from, User to, Date date) {
        fromUser = from;
        toUser = to;
        this.date = date;
        status = RequestStatus.Unread;
    }
}

public class Message {
    private String content;
    private Date date;
    public Message(String content, Date date) {
        this.content = content;
        this.date = date;
    }
}
public abstract class Conversation {
    protected ArrayList<User> participants = new ArrayList<User>();
    protected int id;
    protected ArrayList<Message> messages = new ArrayList<Message>();
    
    public boolean addMessage(Message m) {
        messages.add(m);
        return true;
    }
}
public class PrivateChat extends Conversation {
    public PrivateChat(User user1, User user2) {
        participants.add(user1);
        participants.add(user2);
    }
    
    public User getOtherParticipant(User primary) {
        if (participants.get(0) == primary) {
            return participants.get(1);
        } else if (participants.get(1) == primary) {
            return participants.get(0);
        }
        return null;
    }
}
public class GroupChat extends Conversation {
    public void removeParticipant(User user) {
        participants.remove(user);
    }
    public void addParticipant(User user) {
        participants.add(user);
    }
}