package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n; // /get?from=n

    public GetThread() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Override
    public void run() {
        try {
            String currentUser = Login.getCurrentUser();
            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&username=" + currentUser);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            if (m.getTo() == null || m.getTo().isEmpty()) {
                                System.out.println(m);
                            } else if (m.getTo().equals(currentUser)) {
                                System.out.println("(Private) " + m);
                            } else if (m.getFrom().equals(currentUser)) {
                                System.out.println("(Private to " + m.getTo() + ") " + m);
                            }
                            n++;
                        }
                    }
                } finally {
                    is.close();
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
