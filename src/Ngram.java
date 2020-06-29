import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Ngram {

  public static ArrayList<String> splitToNgrams(String stringToSplit) {

    final int NGRAM_SIZE = 3; // trigrams

    // Convert the string into the Ngrams
    ArrayList<String> listofNGrams = new ArrayList<String>();
    for (int i = 0; i < stringToSplit.length() - 2; i++) {
      listofNGrams.add(stringToSplit.substring(i, i + NGRAM_SIZE));
    }
    return listofNGrams;
  }

  public static ArrayList<String> sortNGrams(ArrayList<String> nGramsToBeSorted) {
    // Sort the nGrams
    Collections.sort(nGramsToBeSorted);
    ArrayList<String> sortedNGrams = nGramsToBeSorted;
    return sortedNGrams;
  }

  public static Hashtable<String, String> countNgrams(ArrayList<String> Ngrams) {

    // Count how many times an nGram occurs within a file
    Hashtable<String, String> gramModel = new Hashtable<String, String>();

    String currentNgram = Ngrams.get(0);
    double itemScore = 0;
    int itemOccurences = 0;

    for (String next : Ngrams) {
      if (next.contentEquals(currentNgram)) {
        itemOccurences++;
      } else {
        double totalSize = Ngrams.size();
        itemScore = itemOccurences / totalSize;

        // Add the key(nGram) and it's respective value(probability)
        gramModel.put(currentNgram, String.valueOf(itemScore));

        // Reset the counter and go to the next biGram
        itemOccurences = 1;
        currentNgram = next;
      }
    }

    return gramModel;
  }

}
