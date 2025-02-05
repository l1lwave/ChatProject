package academy.prog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Register {
    private String login;
    private String password;

    public Register(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void registerClients() {
        try {
            URL url = new URL(Utils.getURL() + "/register");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = "login=" + login + "&password=" + password;
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();

            if (responseCode == 200) {
                System.out.println("Registration successful: " + response);
            } else {
                System.out.println("Registration failed: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
