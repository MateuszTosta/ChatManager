package Chat;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Database {
    private static final Database CONNECTION = new Database();
    private final Set<User> users = new HashSet<>();
    private String loggedIn;

    public static Database getConnection(){
        return CONNECTION;
    }

    public void addUser(User user) throws DBException {
        if(users.stream().anyMatch(u -> u.getName().equals(user.getName()))){
            throw new DBException("Username already exists!");
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        users.add(user);
    }

    public User login(User user) throws DBException {
        Optional<User> userFromDB = users.stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst();
        String hashPassword = DigestUtils.md5Hex(user.getPassword());
        userFromDB.filter(u -> u.getPassword().equals(hashPassword))
                .orElseThrow(() -> new DBException("Wrong username or password."));
        loggedIn = user.getName();
        return userFromDB.get();
    }

    public void logout(){
        loggedIn = null;
    }

    public String getLoggedIn() {
        return loggedIn;
    }
}