import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

public class Identification {

  final static String MODEL_FOLDER = "Models";

  public static void identifyLanguage(String[] content) {

    double bestScore;
    String bestMatch;

    // Detect each model file
    ArrayList<String> modelFiles = new ArrayList<String>();
    File folder = new File(MODEL_FOLDER);
    File[] listOfFile = folder.listFiles();

    // Store each of the model files
    for (File file : listOfFile) {
      if (file.isFile()) {
        modelFiles.add(file.getName());
      }
    }

    // Default value
    bestMatch = "Language not found";
    bestScore = 0;

    // Loop through each model file and store it into a hash-table
    for (int i = 0; i < modelFiles.size(); i++) {
      double totalScore = 0;
      System.out.println("--------------------\nFile: " + modelFiles.get(i));
      Hashtable<String, String> model = Identification.loadModelFile(modelFiles.get(i));

      // Check to see if each triGram is in the model file
      for (String item : content) {
        if (model.containsKey(item)) {
          // Add the score
          totalScore += Double.parseDouble(model.get(item));
        }

        // Check the calculation
        if (totalScore > bestScore) {
          bestScore = totalScore;
          bestMatch = modelFiles.get(i);
        }
      }
      System.out.println("Score: " + totalScore);
    }

    // Output the result
    String[] strippedName = bestMatch.split("Model");
    System.out.println("\n\nIdentified Language: " + strippedName[0] + "\nScore: " + bestScore);
  }

  public static Hashtable<String, String> loadModelFile(String fileName) {

    // Load the model file
    String myFile = IOFile.readUTF8File(MODEL_FOLDER + "/" + fileName);

    // Split each line into an array item
    String[] split = myFile.split("\n");

    // Setup a Hash-table
    Hashtable<String, String> modelHash = new Hashtable<String, String>();

    // Add the items to the current model's Hashtable
    for (String item : split) {

      // Split each item again to create an array where [0] is the key and [1] is the value
      String[] keyValueSplit = item.split(" ");
      String key = keyValueSplit[0];
      String value = keyValueSplit[1];

      // Put the key value pairs into the hash-table
      modelHash.put(key, value);
    }
    return modelHash;

  }
}
