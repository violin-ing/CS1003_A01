import java.nio.file.*;


public class ArgsValidation {

     // Main argument checking method
     public static boolean checkArgs(String[] args) {
          // Run the validation for the args
          if (!lengthChecker(args)) {return false;}
          if (!filePathChecker(args[0])) {return false;}
          if (args[1].equals("type") || args[1].equals("participants") || args[1].equals("key")) {
               if (!valueChecker(args)) {return false;}
          }

          return true;
     }


     // Checks that the args array has the correct number of elements, taking the task into consideration
     public static boolean lengthChecker(String[] args) {
          if (args.length < 2) {
               printUsageMessage();
               return false;
          }

          switch (args[1]) {
               case "type":
               case "participants":
               case "key":
                    if (args.length != 3) {
                         printUsageMessage();
                         return false;
                    } else {
                         break;
                    }
               case "random":
               case "summary":
                    if (args.length != 2) {
                         printUsageMessage();
                         return false;
                    } else {
                         break;
                    }
               default:
                    printUsageMessage();
                    return false;
          }
          
          return true;
     }


     // Checks for a valid file path to the cache directory
     public static boolean filePathChecker(String path) {
          try {
               Path filepath = Paths.get(path);
               if (Files.exists(filepath) && Files.isDirectory(filepath)) {
                    return true;
               } else {
                    System.out.println("Invalid path to cache directory");
                    return false;
               }
          } catch(InvalidPathException exc) {
               System.out.println("Invalid path to cache directory");
               return false;
          }
     }


     // Checks that a correct value is given for its corresponding task
     public static boolean valueChecker(String[] args) {
          switch (args[1]) {
               case "type": 
                    return true;
               case "participants":
               case "key":
                    try {
                         int value = Integer.parseInt(args[2]);
                         return true;
                    } catch (NumberFormatException exc) {
                         System.out.println("Invalid value '" +  args[2] + "' used for '" + args[1] + "' task.");
                         return false;
                    }
               default:
                    printUsageMessage();
                    return false;
          }
     }


     // Method to store the default "usage" message
     public static void printUsageMessage() {
          System.out.println(
                    "Usage: java CS1003Bored pathToCacheDir ['type' | 'participants' | 'key'] value" +
                    "\n       java CS1003Bored pathToCacheDir ['random' | 'summary']"
                    );
     }
}