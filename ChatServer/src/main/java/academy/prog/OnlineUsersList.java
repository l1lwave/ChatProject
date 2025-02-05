package academy.prog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OnlineUsersList {
    private static final Set<String> onlineUsers = Collections.synchronizedSet(new HashSet<>());

    public static void addUser(String login) {
        onlineUsers.add(login);
    }

    public static void removeUser(String login) {
        onlineUsers.remove(login);
    }

    public static Set<String> getOnlineUsers() {
        return onlineUsers;
    }
}
