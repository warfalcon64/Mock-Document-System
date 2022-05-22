package source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Service {

    private static final String DATA_PATH = "C://Users//kkmen//Documents//GitHub//USBank//database";
    Path folderPath = Paths.get(DATA_PATH);
    File folder = folderPath.toFile();    

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    public static ArrayList<Integer> validAccounts = new ArrayList<>();

    public ArrayList<File> searchDatabase(String date) throws IOException {
        ArrayList<File> selectedFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            Path filePath = file.toPath();
            String data = Files.readString(filePath);

            if (data.contains(date)) {
                selectedFiles.add(file);
            }
        }

        return selectedFiles;
    }

    public ArrayList<File> filterDatabase(String date, String filterType) throws IOException, ParseException {
        ArrayList<File> filteredFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            Path filePath = file.toPath();
            String data = Files.readString(filePath);
            Date fileDate = sdf.parse(data.toString());
            Date filterDate = sdf.parse(date);
            
            if (filterType.equalsIgnoreCase("before")) {
                if (fileDate.before(filterDate)) {
                    filteredFiles.add(file);
                }

            } else if (filterType.equalsIgnoreCase("after")) {
                if (fileDate.after(filterDate)) {
                    filteredFiles.add(file);
                }
            }
        }

        return filteredFiles;
    }

    public ArrayList<File> revealDatabase() {
        ArrayList<File> allFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            allFiles.add(file);
        }

        return allFiles;
    }

    public void printNamesOfDocuments(ArrayList<File> documents) {
        int menuOption = 1;

        for (File document : documents) {
            System.out.println("\n" + menuOption + ") " + document.getName());
            menuOption++;
        }
    }

    public Boolean validateUser(int accountNumber) {
        Boolean valid = null;
        for (Integer i: validAccounts) { // it isn't comparing correctly or something
            if (accountNumber == i) {
                valid = true;
            } else {
                valid = false;
            }
        }
        return valid;
    }

    public void initializeClientList() {
        validAccounts.add(1); // 435-628-948-593
        validAccounts.add(2); // 435-628-948-594
    }
}
