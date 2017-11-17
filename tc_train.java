import java.util.*;

public class tc_train {
    public static void main(String[] args) {
        FileHandler stopWordListFile, trainClassListFile, modelFile = null;
        String[] stopWordList = null;
        List<String[]> trainClassList = null;
        if (args.length >= 3) {
            // Read stop-words
            stopWordListFile = new FileHandler(args[0]);
            stopWordListFile.readFile();
            stopWordList = stopWordListFile.getStopWordList();
            // Read train-classes
            trainClassListFile = new FileHandler((args[1]));
            trainClassListFile.readFile();
            trainClassList = trainClassListFile.getTrainClassList();
            // Ready model file
            modelFile = new FileHandler(args[2]);

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

            // LEARN PERCEPTION CLASSIFIERS (ASSUMING SEPARABILITY)
                // 5. Separate training data by classes
                // 6. For each class, learn the perceptron classifier based on text vectors
                    // a) set initial weight (incl. x0, w0)
                    // b) iterate through N samples (do we do passes too?)
                        // i) classify sample with every weight vector (dot product)
                        // ii) after classification, run training rule to update weight vectors
                    // c) obtain converged weight vector for each class

            // OUTPUT PERCEPTRON CLASSIFIERS
                // 7. Output labelled perceptron classifiers to model file
        } else {
            System.err.println("Incorrect number of parameters.");
            System.exit(-1);
        }
    }
}
