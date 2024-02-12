public class CS1003Bored {
     public static void main(String[] args) {
          if (!ArgsValidation.checkArgs(args)) {
               System.exit(1);
          }

          // Perform a certain method depending on the task specified by the user
          switch(args[1]) {
               case "random":
                    Random.getActivity();
                    break;
               case "type":
                    Type.getActivity(args[2]);
                    break;
               case "participants":
                    Participants.getActivity(args[2]);
                    break;
               case "key":
                    Key.getActivity(args[0], args[2]);
                    break;
               case "summary":
                    Summary.getSummary(args[0]);
                    break;
               default:
                    ArgsValidation.printUsageMessage4(args[1]);
                    System.exit(1);
          }

          
     }
}
