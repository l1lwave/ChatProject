package academy.prog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class OnlineUsers {
    private List<String> onlineUsers;

    public void updateOnlineUsers() {
        try {
            URL url = new URL(Utils.getURL() + "/online");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            onlineUsers = gson.fromJson(response.toString(), new TypeToken<List<String>>() {}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getListOfOnlineUsers() {
        return onlineUsers;
    }
}
