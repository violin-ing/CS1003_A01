import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPRequest {

     // Sends HTTP request using the URL (of type String) provided as a parameter
     public static String sendRequest(String urlStr) {
          String returnString = "";
          try {
               URL url = new URL(urlStr);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("GET");

               if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine()) != null) {
                         response.append(line);
                    }

                    reader.close();
                    returnString = response.toString();

               } else {
                    System.out.println("HTTP GET request failed.");
                    System.exit(1);
               }

          } catch (IOException exc) {
               System.out.println("Error occurred when sending HTTP GET request.");
               System.exit(1);
          } 

          return returnString;
     }
}