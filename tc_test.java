import java.util.*;

public class tc_test {
    public static void main(String[] args) {
        FileHandler stopWordListFile, modelFile, testListFile, testClassListFile = null;
        String[] stopWordList = null;
        List<HashMap<String, Integer>> classifiers = null;
        String[] testList = null;
        if (args.length >= 3) {
            // Read stop-words
            stopWordListFile = new FileHandler(args[0]);
            stopWordListFile.readFile();
            stopWordList = stopWordListFile.getStopWordList();
            // Read classifiers
            modelFile = new FileHandler(args[1]);
            classifiers = modelFile.readFileAsModel();
            // Read test-paths
            testListFile = new FileHandler((args[2]));
            testListFile.readFile();
            testList = testListFile.getTestList();
            // Ready test-class-list file
            testClassListFile = new FileHandler(args[3]);

            // PRE-PROCESS
                // 1. Remove the following:
                    // a) frequently occurring stop words
                    // b) words occurring less than k times in the training texts
                    // c) numbers
                    // d) punctuation symbols
                // 2. Stem words using Porter-Stemmer

            // FEATURE SELECTION
                // 3. Calculate chi-square values for each stemmed word in text
                // 4. Calculate normalised frequencies of selected features for text vector

            // CLASSIFY
                // 5. Dot product with each perceptron classifier weight vector
                // 6. Label text with the class whose weight vector gives the greatest dot product value

            // OUTPUT RESULTS
                // 7. Output labelled text to results file
        } else {
            System.err.println("Incorrect number of parameters.");
            System.exit(-1);
        }
    }
}
