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
               printUsageMessage1(args.length);
               return false;
          }

          if (args[1].equals("type") || args[1].equals("participants") || args[1].equals("key")) {
               if (args.length != 3) {
                    printUsageMessage2(args[1], args.length);
               }
          } else if (args[1].equals("random") || args[1].equals("summary")) {
               if (args.length != 2) {
                    printUsageMessage3(args[1], args.length);
               }
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
                    System.out.println("Cache directory does not exist: no_such_cachedir\n" + 
                         "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
                    return false;
               }
          } catch(InvalidPathException exc) {
               System.out.println("Cache directory does not exist: no_such_cachedir\n" + 
                    "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
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
                    return false;
          }
     }


     // Methods to print the error messages
     public static void printUsageMessage1(int argCount) {
          System.out.println(
               "Expected two or three arguments, but got: " + argCount + "\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     public static void printUsageMessage2(String mode, int argCount) {
          System.out.println(
               "Expected a third argument when MODE is set to " + mode + "\n" +
               "But got " + argCount + " arguments\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     public static void printUsageMessage3(String mode, int argCount) {
          System.out.println(
               "Expected a third argument when MODE is set to " + mode + "\n" +
               "But got " + argCount + " arguments\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     public static void printUsageMessage() {
          System.out.println("Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }
}
