# Automatic Language Identification System
A program which can automatically learn languages and identify text based on such languages learnt.

## Using the program
On startup the program first initialises it's *learning* routine where it must process all text files located in the **Learning** folder. Currently I have provided sample texts in the languages English, French, German, Italian and Spanish all of which are just extracts from Wikipedia.org written in their respective languages. Once processed the program outputs these files into the respective **Models** folder which then can be used for the identification process.

	

> To use the program simply insert a file into the **Testing** folder, run the program and select this item from the command line. It should correctly identify the contained language of this file based of the models learnt in the initial startup process.
