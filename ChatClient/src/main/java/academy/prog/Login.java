package academy.prog;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Login {
    private static String login;
    private String password;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean loginStatus() {
        try {
            URL url = new URL(Utils.getURL() + "/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = "username=" + login + "&password=" + password;
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getCurrentUser() {
        return login;
    }
}
