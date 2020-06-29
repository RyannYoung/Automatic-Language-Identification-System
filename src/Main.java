import java.util.ArrayList;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {

    // ===== STARTUP ===== //
    LearningModel model = new LearningModel();
    model.learn();

    // ===== IDENTIFICATION ===== //
    final String TESTING_FOLDER = "Testing";
    String selectFile = IOFile.selectFile(TESTING_FOLDER);

    // Get the selected files details
    String fileName = selectFile;
    String filePath = TESTING_FOLDER + "/" + fileName;

    // Read the contents of the file
    String fileContents = IOFile.readUTF8File(filePath);

    // Split the contents into an ArrayList of words
    ArrayList<String> fileWords =
        LanguageProcessing.extractWords(fileContents, Locale.getDefault());

    // Store all the nGrams for every word into a single list
    ArrayList<String> nGrams = new ArrayList<String>();
    for (int i = 0; i < fileWords.size(); i++) {
      // Split each word into nGram
      ArrayList<String> wordNgram = Ngram.splitToNgrams(fileWords.get(i));
      nGrams.addAll(wordNgram);
    }

    // Convert the list to an array
    String[] nGramArray = new String[nGrams.size()];
    nGrams.toArray(nGramArray);

    // Run the identification
    Identification.identifyLanguage(nGramArray);
  }

}
