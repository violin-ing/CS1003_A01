import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.json.*;
import javax.json.stream.*;


public class Key {

     // Obtain the file with the specified key in the cachedir directory
     // Note that file names are formatted as: {cache-directory}/{key}.json
     public static void getActivity(String cacheDirPath, String key) {
          StringBuffer fullPath = new StringBuffer();
          fullPath.append(cacheDirPath + "/" + key + ".json");
          String fullPathStr = fullPath.toString();

          // Check if the key exists before trying to access the file
          if (verifyFile(fullPathStr)) {
               parseFile(fullPathStr, key);
          } else {
               printErrorMsg(key);
          }
     }

     // Method to check whether the file with the specifed key exists
     private static boolean verifyFile(String filepathStr) {
          File filepath = new File(filepathStr);
          if (filepath.exists()) {
               return true;
          } else {
               return false;
          }
     }

     // Method to parse JSON file
     private static void parseFile(String path, String key) {
          try {
               // Declare variables to store the required data while parsing the JSON file
               String description = "";
               String type = "";
               int pax = 0;

               JsonParserFactory factory = javax.json.Json.createParserFactory(null);
               JsonParser parser = factory.createParser(new FileReader(path));

               while (parser.hasNext()) {
                    JsonParser.Event event = parser.next();
                    if (event == JsonParser.Event.KEY_NAME) {
                         String keyName = parser.getString();
                         parser.next(); // Moves to the corresponding value of the key found
                         switch(keyName) {
                              case "activity":
                                   description = parser.getString();
                                   break;
                              case "participants":
                                   pax = parser.getInt();
                                   break;
                              case "type":
                                   type = parser.getString();
                                   break;
                              default:
                                   break;
                         }
                    }
               }

               printActivity(type, pax, description);
          } catch(FileNotFoundException exc) {
               printErrorMsg(key);
          }
     }

     // Method to print out the activity in the required format
     private static void printActivity(String type, int participants, String activity) {
          System.out.println("Found an activity of type " + type);
          System.out.println("Number of participants needed: " + participants);
          System.out.println("Description: " + activity);
     }

     // Method to print the error message if the file is not found
     private static void printErrorMsg(String key) {
          System.out.println("Unexpected value for key: " + key);
          System.out.println("Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }
}
