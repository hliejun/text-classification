import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FileHandler {
    Path filePath;
    byte[] existingFileData;

    public FileHandler(String pathString) {
        filePath = Paths.get(pathString);
    }

    public void readFile() {
        if (Files.exists(filePath)) {
            try {
                existingFileData = Files.readAllBytes(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File to be read does not exist.");
        }
    }

    public List<HashMap<String, Integer>> readFileAsModel() {
        List<HashMap<String, Integer>> classifiers = null;
        if (Files.exists(filePath)) {
            try {
                FileInputStream fileInput = new FileInputStream(filePath.toString());
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                classifiers = (List<HashMap<String, Integer>>) objectInput.readObject();
                objectInput.close();
                fileInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File to be read does not exist.");
        }
        return classifiers;
    }

    public String getFileAsString() {
        if (existingFileData != null) {
            return new String(existingFileData);
        } else {
            return null;
        }
    }

    public List<String> getStopWordList() {
        if (existingFileData != null) {
            String linesString = new String(existingFileData);
            return new ArrayList<String>(Arrays.asList(linesString.split("\\n+")));
        } else {
            return null;
        }
    }

    public List<String[]> getTrainClassList() {
        if (existingFileData != null) {
            String linesString = new String(existingFileData);
            String[] trainClasses = linesString.split("\\n+");
            List<String[]> taggedPaths = new ArrayList<String[]>();
            for (String trainClass : trainClasses) {
                taggedPaths.add(trainClass.split(" +"));
            }
            return taggedPaths;
        } else {
            return null;
        }
    }

    public String[] getTestList() {
        if (existingFileData != null) {
            String linesString = new String(existingFileData);
            return linesString.split("\\n+");
        } else {
            return null;
        }
    }

    public void writeModel(List<HashMap<String, Integer>> classifiers) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(filePath.toString());
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(classifiers);
            objectOutput.flush();
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
