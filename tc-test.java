import java.util.*;

public class tc_test {
    // Takes in stopword-list, model, test-list, test-class-list
    public static void main(String[] args) {
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
    }
}
