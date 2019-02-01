package Chat;

import java.util.HashMap;
import java.util.Map;

public class User implements Subscriber {
    private String username;
    private String password;
    private Map<Chat, Integer> messagesFromChats = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<Chat, Integer> getMessagesFromChats() {
        return messagesFromChats;
    }

    public void update(Chat chat){
        messagesFromChats.put(chat, messagesFromChats.get(chat)+1);
    }
}