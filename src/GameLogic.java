import java.util.Scanner;

public class GameLogic {
    private String playerClass;
    private int level;
    private String name;
    private Player player;
    private Mage mage;
    private Warrior warrior;
    private Healer healer;

    public GameLogic() {
        level = 1;
        name = "";
    }

    Scanner scan = new Scanner(System.in);

    public void run() {
        introduce();
        createObjects();
        explain();
        mainMenu();
        chooseMenu();
    }

    private void createObjects() {
        player = new Player(playerClass, name);
        if (playerClass.equals("Mage")) {
            mage = new Mage();
        } else if (playerClass.equals("Healer")) {
            healer = new Healer();
        } else if (playerClass.equals("Warrior")) {
            warrior = new Warrior();
        }
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
        System.out.println("3: Upgrade your Player");
        System.out.println("4: Change Name");
        System.out.println("5: Explore Moves");
        System.out.println("6: Quit");
    }

    int choice = -1;

    private void chooseMenu() {
        while (choice != 6) {
            System.out.print("\nChoice #: ");
            choice = scan.nextInt();
            scan.nextLine();
            if (choice == 1) {

            } else if (choice == 2) {

            } else if (choice == 3) {
                upgradeCharacter();
            } else if (choice == 4) {
                System.out.println("Your current name is: " + name);
                System.out.print("What would you like to change it to: ");
                player.setName(scan.nextLine());
            } else if (choice == 5) {
                if (playerClass.equals("Mage")) {
                    System.out.println("Attack: Is a basic attack that damages the opponent.");
                    System.out.println("Your current attack stat is " + mage.getAttack());
                    System.out.println("\nBurn Attack: Is a recurring attack that will tick every round.");
                    System.out.println("Your current burn attack stat is " + mage.getBurnAttack());
                } else if (playerClass.equals("Warrior")) {
                    System.out.println("Attack: Is a basic attack that damages the opponent.");
                    System.out.println("Your current attack stat is " + warrior.getAttack());
                    System.out.println("\nSpecial Attack: Is a heavy attack that will heavily damage the opponent.");
                    System.out.println("Your current special attack stat is " + warrior.getSpecialAttack());
                } else {
                    System.out.println("Attack: Is a basic attack that damages the opponent.");
                    System.out.println("Your current attack stat is " + healer.getAttack());
                    System.out.println("\nHeal: Is a move that will heal your player.");
                    System.out.println("Your current heal stat is " + healer.getHeal());
                }
            } else if (choice == 6) {
                System.out.println("This is just the beginning of your journey. See you again soon!");
            } else {
                System.out.println("Error. Please choose again.");
            }
        }
    }

    private void upgradeCharacter() {
        if (playerClass.equals("Mage")) {
            System.out.println("Attack Stat: " + mage.getAttack());
            System.out.println("Burn Stat: " + mage.getBurnAttack());
            boolean proper = false;
            while (!proper) {
                System.out.print("\nWhat stat would you like to update (Attack/Burn): ");
                String whatUpg = scan.nextLine();
                whatUpg = whatUpg.toLowerCase();
                if (whatUpg.equals("attack")) {
                    proper = true;
                    System.out.print("Attack +1 = 25 Coins. How much would you like to upgrade: ");
                    //add here
                } else if (whatUpg.equals("burn")) {
                    proper = true;
                    System.out.println("Burn +1 = 50 Coins. How much would you like to upgrade: ");
                } else {
                    System.out.println("Not a correct choice! Please enter again.");
                }
            }
        } else if (playerClass.equals("Warrior")) {
            System.out.println("Attack Stat: " + warrior.getAttack());
            System.out.println("Special Attack Stat: " + warrior.getSpecialAttack());
            System.out.print("\nWhat stat would you like to update (Attack/Special): ");
        } else {
            System.out.println("Attack Stat: " + healer.getAttack());
            System.out.println("Heal Stat: " + healer.getHeal());
            System.out.print("\nWhat stat would you like to update (Attack/Heal): ");
        }
    }

}
