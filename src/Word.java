import java.util.Comparator;

public class Word {
     private String word;
     private int count;

     public Word(String word, int count) {
          this.word = word;
          this.count = count;
     }

     // Getter methods
     public String getWord() {
          return word;
     }

     public int getCount() {
          return count;
     }

     // Used to sort the words in the ArrayList in the Summary class
     public static Comparator<Word> counComparator = Comparator.comparing(Word::getCount);
}