import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class LanguageProcessing {



  public static ArrayList<String> extractWords(String inputText, Locale currentLocale) {

    // Get a string and store each word as a element within a list
    ArrayList<String> wordList = new ArrayList<String>();
    BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);

    wordIterator.setText(inputText);
    int start = wordIterator.first();
    int end = wordIterator.next();

    // Gather each word and add it the the wordList
    while (end != BreakIterator.DONE) {
      String word = inputText.substring(start, end);

      word = word.toLowerCase();
      if (Character.isLetter(word.charAt(0)) && word.length() > 1) {
        wordList.add(word);
      }
      start = end;
      end = wordIterator.next();
    }

    return wordList;
  }
}
