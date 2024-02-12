import java.io.StringReader;
import javax.json.*;


public class Type {

     // Gets a specific activity using the base URL "http://www.boredapi.com/api/activity" and the type of activity
     public static void getActivity(String type) {
          StringBuffer urlStrBfr = new StringBuffer();
          urlStrBfr.append("http://www.boredapi.com/api/activity");
          urlStrBfr.append("?type=" + type);
          String urlStr = urlStrBfr.toString();
          String jsonString = HTTPRequest.sendRequest(urlStr);

          // Convert the JSON String into a usable JSON object
          StringReader stringReader = new StringReader(jsonString);
          JsonReader jsonReader = Json.createReader(stringReader);
          JsonObject jsonObject = jsonReader.readObject();
          jsonReader.close();

          // Directly access keys and values to print the formatted output
          if (jsonObject.containsKey("error")) {
               System.out.println("Unexpected value for type: " + type);
               System.out.println("Expected one of: busywork, charity, cooking, diy, education, music, recreational, relaxation, social");
               System.out.println("Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
          } else {
               System.out.println("Found an activity of type " + jsonObject.get("type"));
               System.out.println("Number of participants needed: " + jsonObject.get("participants"));
               System.out.println("Description: " + jsonObject.get("activity"));
          }
     }
}