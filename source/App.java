package source;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        FrontEnd frontEnd = new FrontEnd();
        Service service = new Service();

        boolean exitProgram = false;
        int menuSelection = 6;

        service.initializeClientList();

        if (frontEnd.verifyUserMenu()) {
            menuSelection = frontEnd.mainMenuOptions();
        }

        switch (menuSelection) {
            // Select a document
            case 1:
                try {
                    frontEnd.selectDocumentMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Files could not be found.");
                }
                break;

            // Show all documents
            case 2:
                try {
                    frontEnd.showAllDocumentsMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Files could not be found.");
                }
                break;

            // Show all documents before/after specified date
            case 3:
                try {
                    frontEnd.filterDocuments();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Files could not be found.");
                }
                break;

            // Logout
            case 4:
                exitProgram = true;
                break;

            // Return to main menu
            case 5:
                frontEnd.mainMenuOptions();
                break;

            // Verify user (again)
            case 6:
                frontEnd.verifyUserMenu();
                break;
            default:
                break;
        }
    }
}
