import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class IOFile {



  public static File[] openFiles(String folderName) {

    // Identify the folder to learn from
    File folder = new File(folderName);

    // Store each file in the learning folder into an array
    File[] files = folder.listFiles();

    // Return the File Array
    return files;
  }

  public static String readUTF8File(String filePath) {

    // Open a file and store its text into a single string var
    StringBuilder fileContent = new StringBuilder();

    try {
      Reader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String emptyString;

      // Read each line and append it to the string var
      while ((emptyString = bufferedReader.readLine()) != null) {
        fileContent.append(emptyString + "\n");
      }

      // Close the readers
      bufferedReader.close();
      reader.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return fileContent.toString();
  }

  public static void writeUTF8File(String outputFileFolder, String outputFileName, String content) {

    // Output content to a specified folder
    String outputFilePath = outputFileFolder + "/" + outputFileName;
    try {
      Writer writer =
          new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8);
      PrintWriter printWriter = new PrintWriter(writer);

      // Write each line to the file
      printWriter.print(content);

      writer.close();
      printWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void writeUTF8File(String outputFileFolder, String outputFileName,
      Hashtable<String, String> content) {

    File f = new File("Models");
    try {
      if (f.mkdir()) {
        // System.out.println("Directory Created");
      } else {
        // System.out.println("Created " + outputFileName + "Model.txt");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Output an arraylist to a specified folder (NOTE: THIS IS A DIFFERENT FUNCTION)
    String outputFilePath = outputFileFolder + "/" + outputFileName;
    try {
      Writer writer =
          new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8);

      PrintWriter printWriter = new PrintWriter(writer);

      // CHANGE THIS IF YOU WISH TO OUTPUT THE HASHTABLE SORTED/UNSORTED
      final boolean SORTOUTPUT = true;

      if (SORTOUTPUT) {

        // Convert the hashTable into a TreeMap with a respective Set and Iterator
        TreeMap<String, String> tm = new TreeMap<String, String>(content);
        Set<Entry<String, String>> set = tm.entrySet();
        Iterator<Entry<String, String>> i = set.iterator();

        // Print each key value pair to the file
        while (i.hasNext()) {
          Map.Entry<String, String> me = i.next();
          printWriter.println(me.getKey() + " " + me.getValue());
        }
      } else {
        // Write each line to the file
        content.forEach((key, value) -> printWriter.println(key + " " + value));
      }
      writer.close();
      printWriter.close();

    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  public static String selectFile(String learningFolder) {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Select the file you wish to analyse? (i.e. Unknown2.txt) ");

    // Find the available models
    File folder = new File(learningFolder);
    File[] listOfFile = folder.listFiles();
    for (File file : listOfFile) {
      if (file.isFile()) {
        System.out.println("\t- " + file.getName()); // add the model to the console
      }
    }

    // Prompt the user to select a file
    System.out.print("\nFile: ");
    String selectedFile = userInput.next();
    return selectedFile;

  }

}
