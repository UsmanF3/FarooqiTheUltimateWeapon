import java.util.Scanner;

public class GameLogic {
    private String playerClass;
    private int level;
    private String name;
    private Player player;

    public GameLogic() {
        level = 1;
        name = "";
    }

    Scanner scan = new Scanner(System.in);

    public void run() {
        introduce();
        player = new Player(playerClass);
        explain();
        mainMenu();
    }

    private void introduce() {
        System.out.println("Hello fellow wanderer! Welcome to your great journey!");
        System.out.print("Enter your name: ");
        name = scan.nextLine();
        System.out.println("Pick a class to get started:\n Mage\n Healer\n Warrior");
        while (playerClass == null) {
            askClass();
            selectClass();
        }
    }

    private void askClass() {
        System.out.print("Enter your choice here: ");
        playerClass = scan.nextLine();
    }

    private void selectClass() {
        playerClass = playerClass.toLowerCase();
        if (playerClass.equals("mage")) {
            playerClass = "Mage";
            System.out.println("You are now a mage!");
        } else if (playerClass.equals("healer")) {
            playerClass = "Healer";
            System.out.println("You are now a healer!");
        } else if (playerClass.equals("warrior")) {
            playerClass = "Warrior";
            System.out.println("You are now a warrior!");
        } else {
            playerClass = null;
            System.out.println("You didn't choose a class properly! Please choose again.");
        }
    }

    private void explain() {
        System.out.println();
        System.out.println("You will have to defeat many enemies in order to achieve your goal and get the ultimate weapon.");
        System.out.println("For now, you may start your journey at level one. You will now be redirected to the main menu.");
        System.out.println();
    }

    private void mainMenu() {
        System.out.println("----------Main Menu----------");
        System.out.println("1: Play Level " + level);
        System.out.println("2: View Player Info");
        System.out.println("3: Upgrade your player");
        System.out.println("4: Change Name");
        System.out.println("5: Explore moves");
    }

    private void chooseMenu() {
        System.out.print("Choice #: ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {

        } else if (choice == 2) {

        } else if (choice == 3) {

        } else if (choice == 4) {

        } else if (choice == 5) {

        } else {
            System.out.println("Error. Please choose again.");
        }
    }

}
