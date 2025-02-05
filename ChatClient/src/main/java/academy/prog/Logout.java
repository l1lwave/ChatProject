package academy.prog;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Logout {
    private String login;

    public Logout(String login) {
        this.login = login;
    }

    public void logout() {
        try {
            URL url = new URL(Utils.getURL() + "/logout");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String postData = "login=" + login;
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData.getBytes(StandardCharsets.UTF_8));
            }

            if (conn.getResponseCode() == 200) {
                System.out.println(login + " logged out.");
            } else {
                System.out.println("Logout failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
