import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

public class LearningModel {

  // Load and process the learning models
  public void learn() {

    // Open and store all the learning files
    File[] listOfLanguages = IOFile.openFiles("Learning");

    // Check to see if the files are in the learning folder
    if (listOfLanguages.length == 0) {
      System.out.println("!! ERROR: MISSING LEARNING FILES");
      return;
    }

    // Loop for each file and process it
    for (File file : listOfLanguages) {

      // Get the fileName, path and locale
      String fileName = file.getName();
      String filePath = file.getPath();
      Locale fileLocale = getFileLocale(fileName);

      // Read the contents of the file
      String fileContents = IOFile.readUTF8File(filePath);

      // Split the contents into an array of words[]
      ArrayList<String> fileWords = LanguageProcessing.extractWords(fileContents, fileLocale);

      // Store all the nGrams for every word
      ArrayList<String> nGrams = new ArrayList<String>();

      // Loop through each word split it into triGrams
      for (int i = 0; i < fileWords.size(); i++) {

        // Split each word into its triGrams
        ArrayList<String> wordNgram = Ngram.splitToNgrams(fileWords.get(i));
        nGrams.addAll(wordNgram);
      }

      // Calculate the values and store them into a hashTable
      Hashtable<String, String> nGramHash = Ngram.countNgrams(nGrams);

      // Store the values into their appropriate model file
      String rawName = getRawName(fileName);
      IOFile.writeUTF8File("Models", rawName + "Model.txt", nGramHash);

    }

  }

  public String getRawName(String fileName) {
    String rawName = "";
    if (fileName.contains(".txt")) {
      rawName = fileName.substring(0, fileName.indexOf(".txt"));
    }
    return rawName;
  }

  public Locale getFileLocale(String fileName) {
    // Determines the locale based on the filename
    Locale locale;

    String rawName = getRawName(fileName);

    switch (rawName) {
      case "English":
        locale = Locale.ENGLISH;
        break;
      case "French":
        locale = Locale.FRENCH;
        break;
      case "German":
        locale = Locale.GERMAN;
        break;
      case "Italian":
        locale = Locale.ITALIAN;
        break;
      case "Spanish":
        locale = new Locale("es");
        break;
      default:
        locale = Locale.getDefault();
    }
    return locale;
  }


}
