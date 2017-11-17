import java.util.*;

public class Sample {
    String text = "";
    String sampleClass = "";
    String filePath = "";
    HashMap<String, Integer> sampleWeights = new HashMap<String, Integer>();

    public Sample(String[] params, boolean isDev) {
        FileHandler contentFile;
        if (params.length == 1) {
            filePath = params[0];
        } else if (params.length == 2) {
            filePath = params[0];
            sampleClass = params[1];
        }
        if (isDev) {
            filePath = filePath.replace("/home/course/cs4248/", "");
        }
        if (filePath != "") {
            contentFile = new FileHandler(filePath);
            contentFile.readFile();
            text = contentFile.getFileAsString();
        }
    }

    public String getSampleClass() {
        return sampleClass;
    }

    public HashMap<String, Integer> getSampleWeights(List<String> stopWordsList, int k) {
        parseText(stopWordsList, k);
        return sampleWeights;
    }

    private void parseText(List<String> stopWordsList, int k) {
        // Remove non-word symbols
        text = text.replaceAll("[\\s]+", " ").replaceAll("[^a-zA-Z\\s]", "").toLowerCase().trim();
        // Remove rare words
        List<String> tokenizedText = new ArrayList<String>(Arrays.asList(text.split(" ")));
        HashMap<String, Integer> wordCount = getWordCount(tokenizedText);
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() < k) {
                tokenizedText.removeAll(Collections.singleton(entry.getKey()));
            }
        }
        // Remove stop words
        tokenizedText.removeAll(stopWordsList);
        // Stem words using Porter-Stemmer
        List<String> stemmedText = getStemmedText(tokenizedText);
        sampleWeights = getWordCount(stemmedText);
        System.out.println(sampleWeights);
    }

    private void selectFeatures() {
        // 3. Calculate chi-square values for each stemmed word in text
        // 4. Calculate normalised frequencies of selected features for text weights
    }

    private List<String> getStemmedText(List<String> text) {
        List<String> stemmedText = new ArrayList<String>();
        for (String word : text) {
            Stemmer porterStemmer = new Stemmer();
            porterStemmer.add(word.toCharArray(), word.length());
            porterStemmer.stem();
            String stemmedWord = porterStemmer.toString();
            stemmedText.add(stemmedWord);
        }
        return stemmedText;
    }

    private HashMap<String,Integer> getWordCount(List<String> text) {
        HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
        for (String word : text) {
            Integer currCount = wordCount.get(word);
            if (word == null || word == "" || word.length() == 0) {
                continue;
            } else if (currCount == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, currCount + 1);
            }
        }
        return wordCount;
    }
}
