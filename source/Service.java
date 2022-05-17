package source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Service {
    // "C:/Users/kkmen/Documents/GitHub/USBank/database"
    private static final String DATA_PATH = ""; // <-- this doesn't actually work, fix later
    Path folderPath = Paths.get(DATA_PATH);

    public ArrayList<File> searchDatabase(String date) throws IOException {
        ArrayList<File> selectedFiles = new ArrayList<>();

        for (Path filePath : folderPath) {
            File file = filePath.toFile();

            String data = "";
            data = new String(Files.readAllBytes(filePath));

            if (data.contains(date)) {
                selectedFiles.add(file);
            }
        }

        return selectedFiles;
    }

    public ArrayList<File> filterDatabase(String date, String filterType) throws IOException {
        ArrayList<File> filteredFiles = new ArrayList<>();

        for (Path filePath : folderPath) {
            File file = filePath.toFile();

            String data = new String(Files.readAllBytes(filePath));
            String fileDate = data.split("Date: ").toString();
            
            if (filterType.equalsIgnoreCase("before")) {
                if (fileDate.compareTo(date) < 0) {
                    filteredFiles.add(file);
                }

            } else if (filterType.equalsIgnoreCase("after")) {
                if (fileDate.compareTo(date) > 0) {
                    filteredFiles.add(file);
                }
            }
        }

        return filteredFiles;
    }

    public ArrayList<File> revealDatabase() {
        ArrayList<File> allFiles = new ArrayList<>();

        for (Path filePath: folderPath) {
            File file = filePath.toFile();
            allFiles.add(file);
        }

        return allFiles;
    }

    public void printNamesOfDocuments(ArrayList<File> documents) {
        int menuOption = 1;

        for (File document : documents) {
            System.out.println(menuOption + ") " + document.getName());
            menuOption++;
        }
    }
}
