import java.io.StringReader;
import javax.json.*;


public class Random {

     // Gets a random activity using the fixed URL "http://www.boredapi.com/api/activity"
     public static void getActivity() {
          String jsonString = HTTPRequest.sendRequest("http://www.boredapi.com/api/activity");

          // Convert the JSON String into a usable JSON object
          StringReader stringReader = new StringReader(jsonString);
          JsonReader jsonReader = Json.createReader(stringReader);
          JsonObject jsonObject = jsonReader.readObject();
          jsonReader.close();

          // Directly access keys and values to print the formatted output
          System.out.println("Found an activity of type " + jsonObject.get("type"));
          System.out.println("Number of participants needed: " + jsonObject.get("participants"));
          System.out.println("Description: " + jsonObject.get("activity"));
     }
}
