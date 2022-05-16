package app;

import java.io.IOException;
import java.util.Scanner;

public class FrontEnd {
    Service service = new Service();
    Scanner sc = new Scanner(System.in);

    public int userAccountNum;
    private int mainMenuSelection;

    private boolean validUser = false;
    private boolean loopMainMenu = true;

    public void mainMenu() {
        System.out.println("=== Welcome to Morales Document Storage Services === \n\n");

        while (loopMainMenu) {
            System.out.println("Please sign in with your account number to continue\n");
    
            try {
                userAccountNum = sc.nextInt();
            } catch (Exception e) {
                userAccountNum = 0;
                System.out.println("A valid account number should only contain numerical values");
            }
            
            if (validUser) {
                loopMainMenu = false;           
                mainMenuOptions();

                mainMenuSelection = sc.nextInt();
            } else {
                System.out.println("There was no account found with the given number.\n");
                System.out.println("Exit program? (Y/N)\n");
                String exit = sc.nextLine();
                
                if (exit.equals("Y") || exit.equals("y")) {
                    loopMainMenu = true;
                } else {
                    loopMainMenu = false;
                }
            }
        }
    }

    public void selectDocumentMenu() {
        System.out.println("Please enter the date the document was stored:\n");
        String date = String.valueOf(sc.nextInt());
        
        try {
            service.searchDatabase(date);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error in fetching your document.");
        }
    }

    private void mainMenuOptions() {
        System.out.println("Select an action:\n");
        System.out.println("1) Select A Document\n");
        System.out.println("2) Show All Documents\n");
        System.out.println("3) Logout");
    }
}
