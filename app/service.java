package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Service {
    private static final String DATA_PATH = "C:/Users/kkmen/Documents/GitHub/USBank/database";

    public ArrayList<File> searchDatabase(String date) throws IOException {
        Path folderPath = Paths.get(DATA_PATH);
        ArrayList<File> filteredFiles = new ArrayList<>();

        for (Path filePath : folderPath) {
            File file = filePath.toFile();

            String data = "";
            data = new String(Files.readAllBytes(filePath));

            if (data.contains(date)) {
                filteredFiles.add(file);
            }
        }

        return filteredFiles;
    }
}
