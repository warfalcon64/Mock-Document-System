package source;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontEnd {
    Service service = new Service();
    Scanner sc = new Scanner(System.in);

    public int userAccountNum;
    private int mainMenuSelection;
    
    public static boolean validUser = true;
    
    public int mainMenuOptions() {
        System.out.println("Select an action by entering the corresponding number:\n");
        System.out.println("1) Select A Document\n");
        System.out.println("2) Show All Documents\n");
        System.out.println("3) Show All Documents before/after specified date\n");
        System.out.println("4) Logout\n");
    
        int menuSelection = sc.nextInt();
        return menuSelection;
    }

    public boolean verifyUserMenu() {
        System.out.println("=== Welcome to Morales Document Storage Services === \n");

        System.out.println("Please sign in with your user account number to continue\n");
        userAccountNum = sc.nextInt();

        if (validUser) {
            return true;
        } else {
            System.out.println("Incorrect account number.");
            return false;
        }
    }

    public void selectDocumentMenu() throws IOException { 
        System.out.println("\nPlease enter the date the document was stored:\n");
        String date = String.valueOf(sc.nextInt());
        
        ArrayList<File> documents = service.searchDatabase(date);

        service.printNamesOfDocuments(documents);
    }

    public void showAllDocumentsMenu() {
        System.out.println("\nThese are all of your current documents:");
        System.out.println("Enter the number corresponding to a document to select it\n");

        ArrayList<File> documents = service.revealDatabase();

        service.printNamesOfDocuments(documents);
    }

    public void filterDocuments() throws IOException {
        System.out.println("\nWould you like to filter files before or after a certain date?");
        String filterType = sc.nextLine();

        System.out.println("Please enter the date you would like to filter with:");
        String date = String.valueOf(sc.nextInt());

        ArrayList<File> documents = service.filterDatabase(date, filterType);

        service.printNamesOfDocuments(documents);
    }

    // public int exitProgram() {
    //     System.out.println("Would you like to logout? (Y/N)\n");
    //     String input = sc.nextLine();

    //     if (input.equalsIgnoreCase("Y")) {
    //         return 5;
    //     } else {
    //         return 4;
    //     }
    // }

    public int returnToMenuorLogout() {
        System.out.println("\n Press 'space' to return to the main menu, or any other key to logout");
        String goBack = sc.nextLine();

        if (goBack.equals(" ")) {
            return 5;
        } else {
            return 4;
        }
    }


}