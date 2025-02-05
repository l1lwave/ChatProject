package academy.prog;

public class User {
    private String login;
    private String password;
    private boolean isOnline;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isOnline = false;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline() {
        isOnline = true;
    }

    public void setOffline() {
        isOnline = false;
    }

}
