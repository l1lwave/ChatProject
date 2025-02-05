package academy.prog;

import java.util.HashMap;
import java.util.Map;

public class UserMap {
    public static Map<String, User> users = new HashMap<>();

    public static void addUser(String login, String password) {
        users.put(login, new User(login, password));
    }

    public static User getUser(String login) {
        return users.get(login);
    }
}
