package Chat;

import java.util.HashSet;
import java.util.Set;

public class Chat {
    private String chatName;
    private Set<Subscriber> subscribers = new HashSet<>();
    private String chatHistory = "";

    public Chat(String chatName){
        this.chatName = chatName;
    }

    public void addSubscriber(User user){
        subscribers.add(user);
        user.getMessagesFromChats().put(this, 0);
    }

    public void removeSubscriber(User user){
        subscribers.remove(user);
        user.getMessagesFromChats().remove(this);
    }

    public void notifyAllUsers(){
        subscribers.forEach(u -> u.update(this));
    }

    public String getChatName() {
        return chatName;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void updateChatHistory(String line){
        this.chatHistory+=line+"\n";
    }
}