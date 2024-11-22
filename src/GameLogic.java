import java.util.Scanner;

public class GameLogic {
    private String playerClass;
    private int level;
    private String name;
    private Player player;
    private Mage mage;
    private Warrior warrior;
    private Healer healer;
    private Enemy enemy;

    public GameLogic() {
        level = 1;
        name = "";
    }

    Scanner scan = new Scanner(System.in);

    public void run() {
        introduce();
        createObjects();
        explain();
        chooseMenu();
    }

    private void createObjects() {
        player = new Player(playerClass, name);
        if (playerClass.equals("Mage")) {
            mage = player.getMage();
        } else if (playerClass.equals("Healer")) {
            healer = player.getHealer();
        } else if (playerClass.equals("Warrior")) {
            warrior = player.getWarrior();
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
    }

    private void mainMenu() {
        System.out.println("\n----------Main Menu----------");
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
            mainMenu();
            System.out.print("\nChoice #: ");
            choice = scan.nextInt();
            scan.nextLine();
            System.out.println();
            if (choice == 1) {
                playLevel();
            } else if (choice == 2) {
                playerInfo();
            } else if (choice == 3) {
                upgradeCharacter();
            } else if (choice == 4) {
                changeName();
            } else if (choice == 5) {
                exploreMoves();
            } else if (choice == 6) {
                System.out.println("This is just the beginning of your journey. See you again soon!");
            } else {
                System.out.println("Error. Please choose again.");
            }
        }
    }

    private void resetPlayer(int health) {
        player.setHealth(health);
    }

    private void playLevel() {
        int choice = -1;
        int resetHealth = player.getHealth();
        resetHealth++;
        resetHealth--;
        int levelEnemy = (int) (Math.random()*3) + level;
        enemy = new Enemy(level);
        System.out.println("You have entered level " + level);
        System.out.println("You have encountered a level " + levelEnemy + " ENEMY!");
        while (choice != 4) {
            System.out.println("What will you choose to do?");
            System.out.println("\n-------Fight Menu-------");
            System.out.println("1: Attack");
            System.out.println("2: Defend");
            System.out.println("3: Special Move");
            System.out.println("4: Flee");
            System.out.print("\nFight Option #: ");
            choice = scan.nextInt();
            System.out.println();
            scan.nextLine();
            int randomDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDamageDiff = (int) (Math.random()*3) - 1;
            if (choice == 1) {
                if (playerClass.equals("Mage")) {
                    int amtHit = mage.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        enemy.setHealth(enemy.getHealth() - amtHit + enemy.getDefend());
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        System.out.println("You have successfully attacked the enemy for " + (amtHit - enemy.getDefend()) + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        }
                    }
                } else if (playerClass.equals("Healer")) {
                    int amtHit = healer.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        enemy.setHealth(enemy.getHealth() - amtHit + enemy.getDefend());
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        System.out.println("You have successfully attacked the enemy for " + (amtHit - enemy.getDefend()) + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        }
                    }
                } else if (playerClass.equals("Warrior")) {
                    int amtHit = warrior.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        enemy.setHealth(enemy.getHealth() - amtHit + enemy.getDefend());
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        System.out.println("You have successfully attacked the enemy for " + (amtHit - enemy.getDefend()) + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        }
                    }
                }
            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice != 4){
                System.out.println("Not a proper fight option. Please choose again.");
            }
            if (choice == 4) {
                System.out.println("You have fled. Better luck next time!");
            }
            if (enemy.getHealth()<=0) {
                enemy = null;
                System.out.println("\nThe enemy is DEFEATED! Well Done!");
                int coinsAdd = (int) ((Math.random()*5)+8)*level;
                player.setCoins(player.getCoins() + coinsAdd);
                level++;
                System.out.println("You got " + coinsAdd + " coins!");
                System.out.println("You have unlocked level " + level + "!");
                resetPlayer(resetHealth);
                break;
            } else if (player.getHealth()<=0) {
                System.out.println("\nYou have been DEFEATED. Better luck next time!");
                resetPlayer(resetHealth);
                break;
            }

        }

    }

    private void playerInfo() {
        System.out.println("----------Player Info----------");
        System.out.println("Player Name: " + player.getName());
        System.out.println("Player Coins: " + player.getCoins());
        System.out.println("Player Health: " + player.getHealth() + "HP");
        if (playerClass.equals("Mage")) {
            System.out.println("Player Class: Mage");
            System.out.println("Player Attack Stat: " + mage.getAttack());
            System.out.println("Player Burn Attack Stat: " + mage.getBurnAttack());
            System.out.println("Player Defend Stat: " + player.getDefendStat());
        } else if (playerClass.equals("Healer")) {
            System.out.println("Player Class: Healer");
            System.out.println("Attack Stat: " + healer.getAttack());
            System.out.println("Heal Stat: " + healer.getHeal());
            System.out.println("Player Defend Stat: " + player.getDefendStat());
        } else if (playerClass.equals("Warrior")) {
            System.out.println("Player Class: Warrior");
            System.out.println("Attack Stat: " + warrior.getAttack());
            System.out.println("Special Attack Stat: " + warrior.getSpecialAttack());
            System.out.println("Player Defend Stat: " + player.getDefendStat());
        }

    }

    private void exploreMoves() {
        if (playerClass.equals("Mage")) {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + mage.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nBurn Attack: Is a recurring attack that will tick every round.");
            System.out.println("Your current burn attack stat is " + mage.getBurnAttack());
        } else if (playerClass.equals("Warrior")) {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + warrior.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nSpecial Attack: Is a heavy attack that will heavily damage the opponent.");
            System.out.println("Your current special attack stat is " + warrior.getSpecialAttack());
        } else {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + healer.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nHeal: Is a move that will heal your player.");
            System.out.println("Your current heal stat is " + healer.getHeal());
        }
    }

    private void changeName() {
        System.out.println("Your current name is: " + player.getName());
        System.out.print("What would you like to change it to: ");
        player.setName(scan.nextLine());
        System.out.println("You have successfully changed your name to: " + player.getName());
    }

    private void upgradeCharacter() {
        if (playerClass.equals("Mage")) {
            System.out.println("Attack Stat: " + mage.getAttack());
            System.out.println("Burn Stat: " + mage.getBurnAttack());
            System.out.println("Health Stat: " + player.getHealth());
            System.out.println("Defend Stat: " + player.getDefendStat());
            boolean proper = false;
            while (!proper) {
                System.out.print("\nWhat stat would you like to update (Attack/Burn/Health/Defend): ");
                String whatUpg = scan.nextLine();
                whatUpg = whatUpg.toLowerCase();
                if (whatUpg.equals("attack")) {
                    proper = true;
                    System.out.print("Attack +1 = 25 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*25) {
                            player.setCoins(player.getCoins()-amtUpg*25);
                            mage.setAttack(mage.getAttack()+amtUpg);
                            System.out.println("You have successfully upgraded your attack stat by " + amtUpg + ". Your new attack stat is: " + mage.getAttack());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("burn")) {
                    proper = true;
                    System.out.print("Burn +1 = 50 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*50) {
                            player.setCoins(player.getCoins()-amtUpg*50);
                            mage.setBurnAttack(mage.getBurnAttack()+amtUpg);
                            System.out.println("You have successfully upgraded your burn attack stat by " + amtUpg + ". Your new burn attack stat is: " + mage.getBurnAttack());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("health")) {
                    proper = true;
                    System.out.print("Health +1 = 100 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*100) {
                            player.setCoins(player.getCoins()-amtUpg*100);
                            player.setHealth(player.getHealth()+amtUpg);
                            System.out.println("You have successfully upgraded your health stat by " + amtUpg + ". Your new health stat is: " + player.getHealth() + "HP");
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("defend")){
                    proper = true;
                    System.out.print("Defend +1 = 75 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*75) {
                            player.setCoins(player.getCoins()-amtUpg*75);
                            player.setDefendStat(player.getDefendStat()+amtUpg);
                            System.out.println("You have successfully upgraded your defend stat by " + amtUpg + ". Your new defend stat is: " + player.getDefendStat());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else {
                    System.out.println("Not a correct choice! Please enter again.");
                }
            }
        } else if (playerClass.equals("Warrior")) {
            System.out.println("Attack Stat: " + warrior.getAttack());
            System.out.println("Special Attack Stat: " + warrior.getSpecialAttack());
            System.out.println("Health Stat: " + player.getHealth());
            System.out.println("Defend Stat: " + player.getDefendStat());
            boolean proper = false;
            while (!proper) {
                System.out.print("\nWhat stat would you like to update (Attack/Special/Health/Defend): ");
                String whatUpg = scan.nextLine();
                whatUpg = whatUpg.toLowerCase();
                if (whatUpg.equals("attack")) {
                    proper = true;
                    System.out.print("Attack +1 = 25 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*25) {
                            player.setCoins(player.getCoins()-amtUpg*25);
                            warrior.setAttack(warrior.getAttack()+amtUpg);
                            System.out.println("You have successfully upgraded your attack stat by " + amtUpg + ". Your new attack stat is: " + warrior.getAttack());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("special")) {
                    proper = true;
                    System.out.print("Special Attack +5 = 100 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*100) {
                            player.setCoins(player.getCoins()-amtUpg*100);
                            warrior.setSpecialAttack(warrior.getSpecialAttack()+amtUpg);
                            System.out.println("You have successfully upgraded your special attack stat by " + amtUpg + ". Your new special attack stat is: " + warrior.getSpecialAttack());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("health")) {
                    proper = true;
                    System.out.print("Health +1 = 100 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*100) {
                            player.setCoins(player.getCoins()-amtUpg*100);
                            player.setHealth(player.getHealth()+amtUpg);
                            System.out.println("You have successfully upgraded your health stat by " + amtUpg + ". Your new health stat is: " + player.getHealth() + "HP");
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("defend")) {
                    proper = true;
                    System.out.print("Defend +1 = 75 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*75) {
                            player.setCoins(player.getCoins()-amtUpg*75);
                            player.setDefendStat(player.getDefendStat()+amtUpg);
                            System.out.println("You have successfully upgraded your defend stat by " + amtUpg + ". Your new defend stat is: " + player.getDefendStat());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else {
                    System.out.println("Not a correct choice! Please enter again.");
                }
            }
        } else {
            System.out.println("Attack Stat: " + healer.getAttack());
            System.out.println("Heal Stat: " + healer.getHeal());
            System.out.println("Health Stat: " + player.getHealth());
            System.out.println("Defend Stat: " + player.getDefendStat());
            boolean proper = false;
            while (!proper) {
                System.out.print("\nWhat stat would you like to update (Attack/Heal/Health/Defend): ");
                String whatUpg = scan.nextLine();
                whatUpg = whatUpg.toLowerCase();
                if (whatUpg.equals("attack")) {
                    proper = true;
                    System.out.print("Attack +1 = 25 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*25) {
                            player.setCoins(player.getCoins()-amtUpg*25);
                            healer.setAttack(healer.getAttack()+amtUpg);
                            System.out.println("You have successfully upgraded your attack stat by " + amtUpg + ". Your new attack stat is: " + healer.getAttack());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("heal")) {
                    proper = true;
                    System.out.print("Heal +1 = 50 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*50) {
                            player.setCoins(player.getCoins()-amtUpg*50);
                            healer.setHeal(healer.getHeal()+amtUpg);
                            System.out.println("You have successfully upgraded your heal stat by " + amtUpg + ". Your new heal stat is: " + healer.getHeal());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("health")) {
                    proper = true;
                    System.out.print("Health +1 = 100 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*100) {
                            player.setCoins(player.getCoins()-amtUpg*100);
                            player.setHealth(player.getHealth()+amtUpg);
                            System.out.println("You have successfully upgraded your health stat by " + amtUpg + ". Your new health stat is: " + player.getHealth() + "HP");
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else if (whatUpg.equals("defend")) {
                    proper = true;
                    System.out.print("Defend +1 = 75 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*75) {
                            player.setCoins(player.getCoins()-amtUpg*75);
                            player.setDefendStat(player.getDefendStat()+amtUpg);
                            System.out.println("You have successfully upgraded your defend stat by " + amtUpg + ". Your new defend stat is: " + player.getDefendStat());
                        } else {
                            System.out.println("You can't afford to upgrade this much. Come back when you have more coins!");
                        }
                    } else {
                        System.out.println("Not a proper input.");
                    }
                } else {
                    System.out.println("Not a correct choice! Please enter again.");
                }
            }
        }
    }

}
