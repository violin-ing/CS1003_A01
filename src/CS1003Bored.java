import java.nio.file.Path;
import java.nio.file.Paths;


public class CS1003Bored {
     public static void main(String args[]) {
          if (!ArgsValidation.checkArgs(args)) {
               System.exit(1);
          }

          // Setting variables to eventually pass to methods in other classes
          Path dirPath = Paths.get(args[0]);
          String task = args[1];
          if (args.length == 3) {
               String value = args[2];
          }

          // Perform a certain method depending on the task specified by the user
          switch(task) {
               case "random":
               case "type":
               case "participant":
               case "key":
               case "summary":
               default:
                    ArgsValidation.printUsageMessage();
                    System.exit(1);
          }

          
     }
}
