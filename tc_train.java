import java.util.*;

public class tc_train {
    public static void main(String[] args) {
        FileHandler stopWordListFile, trainClassListFile, modelFile = null;
        List<String> stopWordList = null;
        List<String[]> trainClassList = null;
        HashMap<String, List<HashMap<String, Integer>>> classifiedWeights = new HashMap<String, List<HashMap<String, Integer>>>();
        if (args.length >= 3) {
            // Read stop-words
            stopWordListFile = new FileHandler(args[0]);
            stopWordListFile.readFile();
            stopWordList = stopWordListFile.getStopWordList();
            // Read train-classes
            trainClassListFile = new FileHandler((args[1]));
            trainClassListFile.readFile();
            trainClassList = trainClassListFile.getTrainClassList();
            // Populate samples
            for (String[] trainClass : trainClassList) {
                // TODO: Set boolean to false on submission
                Sample sample = new Sample(trainClass, true);
                String sampleClass = sample.getSampleClass();
                HashMap<String, Integer> sampleWeights = sample.getSampleWeights(stopWordList, 2);
                List<HashMap<String, Integer>> weightsList = classifiedWeights.get(sampleClass);
                if (weightsList == null) {
                    weightsList = new ArrayList<HashMap<String, Integer>>();
                }
                weightsList.add(sampleWeights);
                classifiedWeights.put(sampleClass, weightsList);
            }
            // Learn classification
            // TODO: Use perceptron classifier to create classifiers
            // Ready model file
            modelFile = new FileHandler(args[2]);
            // TODO: write model to file
        } else {
            System.err.println("Incorrect number of parameters.");
            System.exit(-1);
        }
    }
}
