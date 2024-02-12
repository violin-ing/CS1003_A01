import java.nio.file.*;


public class ArgsValidation {

     // Main argument checking method
     public static boolean checkArgs(String[] args) {
          // Run the validation for the args
          if (!lengthChecker(args)) {return false;}
          if (!filePathChecker(args[0])) {return false;}

          return true;
     }


     // Checks that the args array has the correct number of elements, taking the task into consideration
     private static boolean lengthChecker(String[] args) {
          if (args.length < 2) {
               printUsageMessage1(args.length);
               return false;
          }

          if (args[1].equals("type") || args[1].equals("participants") || args[1].equals("key")) {
               if (args.length != 3) {
                    printUsageMessage2(args[1], args.length);
                    return false;
               }
          } else if (args[1].equals("random") || args[1].equals("summary")) {
               if (args.length != 2) {
                    printUsageMessage3(args[1], args.length);
                    return false;
               }
          } else {
               printUsageMessage4(args[1]);
               return false;
          }
          
          return true;
     }


     // Checks for a valid file path to the cache directory
     private static boolean filePathChecker(String path) {
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


     // Methods to print the error messages
     private static void printUsageMessage1(int argCount) {
          System.out.println(
               "Expected two or three arguments, but got: " + argCount + "\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     private static void printUsageMessage2(String mode, int argCount) {
          System.out.println(
               "Expected a third argument when MODE is set to " + mode + "\n" +
               "But got " + argCount + " arguments\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     private static void printUsageMessage3(String mode, int argCount) {
          System.out.println(
               "Expected two arguments when MODE is set to " + mode + "\n" +
               "But got " + argCount + " arguments\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }

     public static void printUsageMessage4(String mode) {
          System.out.println(
               "Unexpected value for MODE: " + mode + "\n" +
               "Expected one of: random, type, participants, key, summary\n" +
               "Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
     }
}
