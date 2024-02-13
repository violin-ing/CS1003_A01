import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;


public class Summary {
     // Declare a HashMap, which will be used for storing and incrementing the count of each new word found 
     public static HashMap<String, Integer> wordCount = new HashMap<>();

     // Iterates through the given cache directory and counts each words in the activity descriptions
     public static void getSummary(String cacheDirPath) {
          File cacheDir = new File(cacheDirPath);
          File[] fileList = cacheDir.listFiles();

          for (File file : fileList) {
               if (file.isFile()) {
                    parseFile(file.getAbsolutePath());
               }
          }

          printWordCount();
     }

     // Method to parse each JSON file
     private static void parseFile(String path) {
          try {
               JsonParserFactory factory = javax.json.Json.createParserFactory(null);
               JsonParser parser = factory.createParser(new FileReader(path));
               
               while (parser.hasNext()) {
                    JsonParser.Event event = parser.next();
                    if (event == JsonParser.Event.KEY_NAME) {
                         String keyName = parser.getString();
                         parser.next();
                         if (keyName.equals("activity")) {
                              String rawActivityDesc = parser.getString();
                              countWords(rawActivityDesc);
                         }
                    }
               }
          } catch(FileNotFoundException exc) {
               System.out.println("Usage: java CS1003Bored CACHEDIR MODE [VALUE]");
               System.exit(1);
          }
     }

     // Method to do word counting, which will update the map
     private static void countWords(String text) {
          String formattedText = text.replaceAll("[^a-zA-Z0-9]", " ");
          String lowerCaseText = formattedText.toLowerCase();
          String[] words = lowerCaseText.split("\\s+");
          for (String word : words) {
               wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
          }
     }

     // Method to output the word count of the top 10 most common words
     private static void printWordCount() {
          ArrayList<Word> wordList = new ArrayList<>();

          for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
               wordList.add(new Word(entry.getKey(), entry.getValue()));
          }

          // Now the words will be sorted in decreasing order
          Collections.sort(wordList, Word.countComparator);
          Collections.reverse(wordList);

          System.out.println("Most frequent words in the activity fields:");
          for (int i = 0; i < 10; i++) {
               Word currentWord = wordList.get(i);
               System.out.println("\t" + currentWord.getCount() + "\t" + currentWord.getWord());
          }
     }
}