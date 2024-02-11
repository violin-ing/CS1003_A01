import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.*;
import javax.json.stream.*;


public class Random {
     public static void sendRequest() {
          try {
               URL url = new URL("https://www.boredapi.com/api/activity");

               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("GET");
               if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = reader.readLine()) != null) {
                         response.append(inputLine);
                    }
                    reader.close();
                    String responseString = response.toString();

                    formatActivity(responseString);
               } else {
                    System.out.println("HTTP GET request failed.");
                    System.exit(1);
               }
          } catch (IOException exc) {
               System.out.println("Error occurred when sending HTTP GET request.");
               System.exit(1);
          }
     }


     public static void formatActivity(String jsonString) {

     }
}