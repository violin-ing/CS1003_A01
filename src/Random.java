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

               HttpURLConnection con = (HttpURLConnection) url.openConnection();
               con.setRequestMethod("GET");
               int responseCode = con.getResponseCode();
               System.out.println("GET Response Code :: " + responseCode);
               if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                         response.append(inputLine);
                    }
                    in.close();
                    System.out.println(response.toString());
               } else {
                    System.out.println("GET request failed.");
               }

          } catch(IOException exc) {
               System.out.println("Error occurred when sending HTTP request.");
               System.exit(1);
          }
          
     }
}