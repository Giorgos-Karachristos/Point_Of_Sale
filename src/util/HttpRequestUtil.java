package util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpRequestUtil {

    private static final Logger LOGGER = Logger.getLogger(HttpRequestUtil.class.getName());
    private static final String API_URL = "https://users.it.teithe.gr/~it185192/pos/pos.php/";

    public static String sendHttpRequest(String endpoint, String method, JSONObject jsonInput) {
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL + endpoint).openConnection();
            connection.setRequestMethod(method);

            // Set the request content type to JSON
            connection.setRequestProperty("Content-Type", "application/json; utf-8");

            if (jsonInput != null) {
                // Enable output streams
                connection.setDoOutput(true);
                // Convert the JSON object to bytes and send it in the request
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(jsonInput.toString().getBytes(StandardCharsets.UTF_8));
                }
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    String responseLine;
                    while ((responseLine = bufferedReader.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
            } else {
                LOGGER.log(Level.SEVERE, "{0} request failed. Response Code: {1}", new Object[]{method, responseCode});
                return null;
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Network error in " + method + " request", e);
        }
        String result = response.toString();
        if (result.startsWith("\"") && result.endsWith("\"")) {
            result = result.substring(1, result.length() - 1);
        }
        return result;
    }
}

