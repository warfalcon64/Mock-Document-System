package app;

import java.util.Scanner;

public class frontEnd {
    Scanner sc = new Scanner(System.in);

    public int userAccountNum;
    private int mainMenuSelection;

    private boolean validUser = false;
    private boolean loopMainMenu = true;

    public void mainMenu() {
        System.out.println("=== Welcome to Morales Bank === \n\n");

        while (loopMainMenu) {
            System.out.println("Please sign in with your account number to continue");
    
            try {
                userAccountNum = sc.nextInt();
            } catch (Exception e) {
                userAccountNum = 0;
                System.out.println("A valid account number should only contain numerical values");
            }
            
            if (validUser) {
                loopMainMenu = false;
                
                System.out.println("Select an action:\n");
                System.out.println("1) Deposit Document\n");
                System.out.println("2) Withdraw Document\n");
                System.out.println("3) Logout");

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

    public void depositMenu() {
        System.out.println("");
    }
}
