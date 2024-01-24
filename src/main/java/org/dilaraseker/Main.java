package org.dilaraseker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
public class Main {

    public static void main(String[] args) {
        sendGetRequest();
        sendPostRequest();
    }

    private static void sendGetRequest() {
        try {
            String apiUrl = "https://jsonplaceholder.typicode.com/users/7";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("GET Request Sent");
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("GET request was successful (200 OK).");
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Requested resource not found for GET (404 Not Found).");
            } else {
                System.out.println("Unknown HTTP status for GET: " + responseCode);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("GET Response: " + response.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPostRequest() {
        try {
            String apiUrl = "https://jsonplaceholder.typicode.com/users/";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                // POST data
                String postData = "{ \"name\": \"Deneme User\", \"username\": \"denemeuser\", \"email\": \"deneme@gmail.com\" }";
                connection.setDoOutput(true);

                // Write data
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = postData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                System.out.println("POST Request Sent");
                System.out.println("Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_CREATED) {
                    System.out.println("POST request was successful (201 Created).");
                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("Requested resource not found for POST (404 Not Found).");
                }else {
                    System.out.println("Unknown HTTP status for POST: " + responseCode);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println("POST Response: " + response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}