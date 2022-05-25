package source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    public ArrayList<File> searchDatabase(String date, int accountNumber) throws IOException {
        ArrayList<File> selectedFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            Path filePath = file.toPath();
            String data = Files.readString(filePath);
            String fileDate = data.split("\n")[0];
            String fileAccountNum = data.split("\n")[1];

            if (fileDate.contains(date) && fileAccountNum.equals(String.valueOf(accountNumber))) {
                selectedFiles.add(file);
            }
        }

        return selectedFiles;
    }

    public ArrayList<File> filterDatabase(String date, String filterType, int accountNumber) throws IOException, ParseException {
        ArrayList<File> filteredFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            Path filePath = file.toPath();
            String data = Files.readString(filePath);
            String fileAccountNum = data.split("\n")[1];

            Date fileDate = sdf.parse(data.toString());
            Date filterDate = sdf.parse(date);
            
            if (filterType.equalsIgnoreCase("before") && fileAccountNum.equals(String.valueOf(accountNumber))) {
                if (fileDate.before(filterDate)) {
                    filteredFiles.add(file);
                }

            } else if (filterType.equalsIgnoreCase("after") && fileAccountNum.equals(String.valueOf(accountNumber))) {
                if (fileDate.after(filterDate)) {
                    filteredFiles.add(file);
                }
            }
        }

        return filteredFiles;
    }

    public ArrayList<File> revealDatabase(int accountNumber) throws IOException {
        ArrayList<File> allFiles = new ArrayList<>();
        
        for (File file : folder.listFiles()) {
            Path filePath = file.toPath();
            String data = Files.readString(filePath);
            String fileAccountNum = data.split("\n")[1];

            if (fileAccountNum.equals(String.valueOf(accountNumber))) {
                allFiles.add(file);
            }
        }

        return allFiles;
    }

    public void downloadFile(File document) throws IOException {
        String path = "C:/Users/kkmen/Downloads/" + document.getName();
        File downloadedDocument = new File(path);
        InputStream in = document.toURI().toURL().openStream();
        Files.copy(in, downloadedDocument.toPath());
    }

    public void printNamesOfDocuments(ArrayList<File> documents) {
        int menuOption = 1;

        for (File document : documents) {
            System.out.println("\n" + menuOption + ") " + document.getName());
            menuOption++;
        }
    }

    public boolean validateUser(int accountNumber) {
        boolean valid = false;
        for (int i = 0; i < validAccounts.size(); i++) { // it isn't comparing correctly or something
            if (accountNumber == validAccounts.get(i)) {
                valid = true;
            }
        }
        return valid;
    }

    public void initializeClientList() {
        validAccounts.add(1); // 435-628-948-593
        validAccounts.add(2); // 435-628-948-594    
    }
}
