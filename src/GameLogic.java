import java.util.Scanner;

public class GameLogic {
    private String playerClass;
    private int level;
    private boolean bossDefeated;
    private String bosskey;
    private String ultBook;
    private String name;
    private Player player;
    private Mage mage;
    private Warrior warrior;
    private Healer healer;
    private Enemy enemy;

    public GameLogic() {
        level = 1;
        name = "";
        bossDefeated = false;
        bosskey = null;
        ultBook = null;
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.print("Enter your name: ");
        name = scan.nextLine();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println("Pick a class to get started:\n Mage\n Healer\n Warrior");
        while (playerClass == null) {
            askClass();
            selectClass();
        }
    }

    private void askClass() {
        System.out.print("\nEnter your choice here: ");
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println();
        System.out.println("You will have to defeat many enemies in order to achieve your goal and get the ultimate weapon.");
        System.out.println("For now, you may start your journey at level one. You will now be redirected to the main menu.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void mainMenu() {
        System.out.println("\n----------Main Menu----------");
        System.out.println("1: Play Level " + level);
        System.out.println("2: View Player Info");
        System.out.println("3: Upgrade your Player");
        System.out.println("4: Open Inventory");
        System.out.println("5: Change Name");
        System.out.println("6: Explore Moves");
        System.out.println("7: Quit");
    }

    int choice = -1;

    private void chooseMenu() {
        while (choice != 7) {
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
                openInventory();
            } else if (choice == 5) {
                changeName();
            } else if (choice == 6) {
                exploreMoves();
            } else if (choice == 7) {
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
        int hitCounter = 0;
        boolean used = false;
        boolean said = false;
        int resetHealth = player.getHealth();
        resetHealth++;
        resetHealth--;
        int levelEnemy = (int) (Math.random()*3) + level;
        enemy = new Enemy(level);
        System.out.println("You have entered level " + level);
        System.out.println("You have encountered a level " + levelEnemy + " ENEMY!");
        while (choice != 4) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("\nWhat will you choose to do?");
            System.out.println("\n-------Fight Menu-------");
            System.out.println("1: Attack");
            System.out.println("2: Defend");
            System.out.println("3: Special Move");
            System.out.println("4: Flee");
            System.out.print("\nFight Option #: ");
            choice = scan.nextInt();
            System.out.println();
            scan.nextLine();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            boolean notCheese = true;
            int randomDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDamageDiff = (int) (Math.random()*3) - 1;
            if (choice == 1) {
                if (playerClass.equals("Mage")) {
                    int amtHit = mage.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        amtHit = mage.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
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
                        amtHit = healer.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
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
                        amtHit = warrior.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the enemy for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the enemy for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
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
                if (playerClass.equals("Mage")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the enemy also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                } else if (playerClass.equals("Healer")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the enemy also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                } else if (playerClass.equals("Warrior")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The enemy STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the enemy also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The enemy SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The enemy SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                }
            } else if (choice == 3) {
                if (!used) {
                    used = true;
                    if (playerClass.equals("Mage")) {
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            System.out.println("You have successfully used your BURN ATTACK! The enemy is BURNING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            System.out.println("You have successfully used your BURN ATTACK!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy defended but you BROKE his BLOCK! The enemy is BURNING!");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            if (enemySpecialHit<0)
                                enemySpecialHit = 0;
                            System.out.println("You have successfully used your BURN ATTACK! The enemy is BURNING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            int doesItHit = (int) (Math.random()*3) - 1;
                            if (doesItHit == 0) {
                                player.setHealth(player.getHealth()-enemySpecialHit);
                                System.out.println("The enemy SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            } else {
                                System.out.println("The enemy SPECIAL STRIKES back but it MISSED!");
                            }
                        }
                    } else if (playerClass.equals("Healer")) {
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()+healer.getHeal());
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            player.setHealth(player.getHealth()+healer.getHeal());
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy defended but it did NOTHING!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()+healer.getHeal());
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            int doesItHit = (int) (Math.random()*3) - 1;
                            if (doesItHit == 0) {
                                player.setHealth(player.getHealth()-enemySpecialHit);
                                System.out.println("The enemy SPECIAL STRIKES and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            } else {
                                System.out.println("The enemy SPECIAL STRIKES but it MISSED!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            }
                        }
                    } else if (playerClass.equals("Warrior")) {
                        int specialAmtHit = warrior.getSpecialAttack()+randomDiff;
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK! You've hit the enemy for " + specialAmtHit + " damage!");
                            System.out.println("The enemy is BLEEDING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The enemy defended but you BROKE his BLOCK!  You've hit the enemy for " + specialAmtHit + " damage!");
                            System.out.println("The enemy is BLEEDING!");
                            System.out.println("The enemy stands on " + enemy.getHealth() + "HP");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            if (enemySpecialHit<0)
                                enemySpecialHit = 0;
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK! You've hit the enemy for " + specialAmtHit + " damage!");
                            System.out.println("The enemy is BLEEDING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
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
                } else {
                    System.out.println("You've already used your special move!");
                    notCheese = false;
                }
            } else if (choice != 4){
                System.out.println("Not a proper fight option. Please choose again.");
                notCheese = false;
            }
            if (choice == 4) {
                System.out.println("You have fled. Better luck next time!");
                resetPlayer(resetHealth);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (used&&playerClass.equals("Mage")&&notCheese) {
                if (hitCounter<=4) {
                    hitCounter++;
                    enemy.setHealth(enemy.getHealth()-mage.getBurnAttack());
                    System.out.println("\nThe enemy has been BURNED for " + mage.getBurnAttack() + "HP");
                    System.out.println("The enemy stands on " + enemy.getHealth() + "HP!");
                } else if (hitCounter==5){
                    if (!said) {
                        System.out.println("\nThe burn has worn OFF!");
                        said = true;
                    }
                }
            }
            if (used&&playerClass.equals("Warrior")&&notCheese) {
                if (hitCounter<=1) {
                    hitCounter++;
                    enemy.setHealth(enemy.getHealth()-(warrior.getAttack()/2));
                    System.out.println("\nThe enemy bled " + (warrior.getAttack()/2) + "HP");
                    System.out.println("The enemy stands on " + enemy.getHealth() + "HP!");
                } else if (hitCounter==2){
                    if (!said) {
                        System.out.println("\nThe wound has healed!");
                        said = true;
                    }
                }
            }
            if (used&&playerClass.equals("Healer")&&notCheese) {
                if (hitCounter<=2) {
                    hitCounter++;
                    player.setHealth(player.getHealth()+(healer.getHeal()/3));
                    System.out.println("\nYour healing essence has healed you for " + (healer.getHeal()/3) + "HP");
                    System.out.println("You are now on " + player.getHealth() + "HP!");
                } else if (hitCounter==3){
                    if (!said) {
                        System.out.println("\nThe healing essence has dispersed!");
                        said = true;
                    }
                }
            }
            if (enemy.getHealth()<=0) {
                enemy = null;
                System.out.println("\nThe enemy is DEFEATED! Well Done!");
                int coinsAdd = (int) ((Math.random()*10)+15)*level;
                player.setCoins(player.getCoins() + coinsAdd);
                level++;
                System.out.println("You got " + coinsAdd + " coins!");
                System.out.println("You have unlocked level " + level + "!");
                if (level==11) {
                    bosskey = "obtained";
                    System.out.println("\nThe enemy dropped an item. It looks like a key with cryptic writing all over it...");
                    System.out.println("Obtained: ???");
                }
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
        System.out.println("Player Coins: " + player.getCoins() + " Coins");
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

    private void openInventory() {
        System.out.println("--------Inventory--------");
        System.out.println("1. Coins: " + player.getCoins());
        if (playerClass.equals("Mage")) {
            System.out.println("2. Magic Spellbook");
        } else if (playerClass.equals("Healer")) {
            System.out.println("2. Wand");
        } else if (playerClass.equals("Warrior")){
            System.out.println("2. Sword and Shield");
        }
        System.out.println("3. Pocket Watch");
        if (bosskey!=null)
            System.out.println("4. Ancient Key");
        if (ultBook!=null)
            System.out.println("5. The Ultimate Book");
        System.out.println("-1: Exit Inventory");
        int itmChoice=0;
        while (itmChoice!=-1) {
            System.out.print("\nWhich # item would you like to interact with: ");
            itmChoice = scan.nextInt();
            scan.nextLine();
            System.out.println();
            if (itmChoice==1) {
                if (player.getCoins()>0) {
                    System.out.println("Shiny Golden Coins. Looks like they could be valuable.");
                } else {
                    System.out.println("There's nothing in the coin pouch!");
                }
            } else if (itmChoice==2) {
                if (playerClass.equals("Mage")) {
                    System.out.println("A magical spellbook with powers beyond that of a regular human..");
                } else if (playerClass.equals("Healer")) {
                    System.out.println("A strange looking wand forged from twisted vines. It's core is glowing.");
                } else if (playerClass.equals("Warrior")){
                    System.out.println("An extremely sharp sword paired with an extremely sturdy shield. Capable of inflicting much harm.");
                }
            } else if (itmChoice==3) {
                System.out.println("A new pocket watch. Don't remember how it got there.. The year reads: 1822.");
            } else if (itmChoice==4) {
                if (bosskey!=null) {
                    if (!bossDefeated) {
                        System.out.println("It's the ancient key the enemy dropped..");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("\nThe key is getting extremely hot!! You throw it on the floor!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("It's SHAKING and GLOWING AGGRESSIVELY!!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("\nYOU'RE BEING TELEPORTED!!!!!!!");
                        System.out.print("\n.");
                        for (int i = 0; i<3; i++) {
                            try {
                                Thread.sleep(1000);
                                System.out.print(".");
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        bossFight();
                        break;
                    } else {
                        System.out.println("The boss portal key... It's dull and cold now.");
                    }
                } else {
                    System.out.println("Invalid option!");
                }
            } else if (itmChoice==5) {
                if (ultBook!=null) {
                    System.out.println("It's an old, dusty book with cryptic writing all over the front cover.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("You open it up..");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("\nYou can't read anything!");
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("Wait! The text starts to GLOW BRIGHTLY!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("You can understand it now?!?!");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("\n--------The Ultimate Weapon--------");
                    System.out.println("You've finally obtained the ultimate weapon in this multiverse...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("\nThe ultimate weapon is the strength, wisdom, and endurance you built on this journey.. In other words.. the ultimate weapon is.. ");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("\n... You.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("\nThanks for playing!");
                    break;
                } else {
                    System.out.println("Invalid option!");
                }
            } else if (itmChoice!=-1){
                System.out.println("Invalid option!");
            }
        }
    }

    private void bossFight() {
        int choice = -1;
        int hitCounter = 0;
        boolean used = false;
        boolean said = false;
        int resetHealth = player.getHealth();
        resetHealth++;
        resetHealth--;
        enemy = new Enemy("boss");
        System.out.println("\n\nYou're on a floating island made of rock, with other islands all around you.");
        System.out.println("It seems like you are in a remote galaxy in space..");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println("\nAN EXTREMELY LARGE ENEMY WITH A JEWELED CROWN ON HIS HEAD APPEARS BEFORE YOU!!");
        System.out.println("HE LOOKS EXTREMELY POWERFUL!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println("\n???: \"WHO DARE AWAKEN ME FROM MY SLUMBER!!!\"");
        System.out.println("???: \"YOU WILL PAY THE PRICE!!!\"");
        System.out.print("\n.");
        for (int i = 0; i<3; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("\n\nYou've encountered the ASTRAL BOSS!!!");
        while (choice != 4) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("\nWhat will you choose to do?");
            System.out.println("\n-------Fight Menu-------");
            System.out.println("1: Attack");
            System.out.println("2: Defend");
            System.out.println("3: Special Move");
            System.out.println("4: Flee");
            System.out.print("\nFight Option #: ");
            choice = scan.nextInt();
            System.out.println();
            scan.nextLine();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            boolean notCheese = true;
            int randomDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDiff = (int) (Math.random()*3) - 1;
            int randomEnemyDamageDiff = (int) (Math.random()*3) - 1;
            if (choice == 1) {
                if (playerClass.equals("Mage")) {
                    int amtHit = mage.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        amtHit = mage.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the boss for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        }
                    }
                } else if (playerClass.equals("Healer")) {
                    int amtHit = healer.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        amtHit = healer.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the boss for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        }
                    }
                } else if (playerClass.equals("Warrior")) {
                    int amtHit = warrior.getAttack() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        amtHit = warrior.getAttack() + randomDiff - enemy.getDefend();
                        if (amtHit<0)
                            amtHit=0;
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        System.out.println("You attacked the boss for " + amtHit + " damage but he DEFENDED!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES back! You've been hit for " + enemyAmtHit + " damage!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                        System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                    } else if (randomEnemyDiff == 1){
                        enemy.setHealth(enemy.getHealth() - amtHit);
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                        System.out.println("You have successfully attacked the boss for " + amtHit + " health!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED!");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        }
                    }
                }
            } else if (choice == 2) {
                if (playerClass.equals("Mage")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the boss also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                } else if (playerClass.equals("Healer")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the boss also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                } else if (playerClass.equals("Warrior")) {
                    int amtDefend = player.getDefendStat() + randomDiff;
                    if (randomEnemyDiff == -1) {
                        int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff-amtDefend;
                        if (enemyAmtHit<0)
                            enemyAmtHit=0;
                        player.setHealth(player.getHealth()-enemyAmtHit);
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("The boss STRIKES! However since you defended, You've been hit for " + enemyAmtHit + " damage instead of " + (enemyAmtHit+amtDefend) + "!");
                        System.out.println("You are now on " + player.getHealth() + "HP");
                    } else if (randomEnemyDiff == 0) {
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        System.out.println("But the boss also DEFENDED! Nothing happened!");
                    } else if (randomEnemyDiff == 1){
                        int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff - amtDefend;
                        if (enemySpecialHit<0)
                            enemySpecialHit = 0;
                        System.out.println("You have successfully defended " + amtDefend + " damage!");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        int doesItHit = (int) (Math.random()*3) - 1;
                        if (doesItHit == 0) {
                            player.setHealth(player.getHealth()-enemySpecialHit);
                            System.out.println("The boss SPECIAL STRIKES back and it LANDED! But since you defended, You've been hit for " + enemySpecialHit + " damage instead of " + (enemySpecialHit+amtDefend) + "!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else {
                            System.out.println("The boss SPECIAL STRIKES back but it MISSED! Nothing happened!");
                        }
                    }
                }
            } else if (choice == 3) {
                if (!used) {
                    used = true;
                    if (playerClass.equals("Mage")) {
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            System.out.println("You have successfully used your BURN ATTACK! The boss is BURNING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            System.out.println("You have successfully used your BURN ATTACK!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss defended but you BROKE his BLOCK! The boss is BURNING!");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            if (enemySpecialHit<0)
                                enemySpecialHit = 0;
                            System.out.println("You have successfully used your BURN ATTACK! The boss is BURNING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            int doesItHit = (int) (Math.random()*3) - 1;
                            if (doesItHit == 0) {
                                player.setHealth(player.getHealth()-enemySpecialHit);
                                System.out.println("The boss SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            } else {
                                System.out.println("The boss SPECIAL STRIKES back but it MISSED!");
                            }
                        }
                    } else if (playerClass.equals("Healer")) {
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()+healer.getHeal());
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            player.setHealth(player.getHealth()+healer.getHeal());
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss defended but it did NOTHING!");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()+healer.getHeal());
                            System.out.println("You have successfully used your HEAL! You've healed " + healer.getHeal() + "HP!");
                            System.out.println("Your healing essence is in the air!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            int doesItHit = (int) (Math.random()*3) - 1;
                            if (doesItHit == 0) {
                                player.setHealth(player.getHealth()-enemySpecialHit);
                                System.out.println("The boss SPECIAL STRIKES and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            } else {
                                System.out.println("The boss SPECIAL STRIKES but it MISSED!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                            }
                        }
                    } else if (playerClass.equals("Warrior")) {
                        int specialAmtHit = warrior.getSpecialAttack()+randomDiff;
                        if (randomEnemyDiff == -1) {
                            int enemyAmtHit = enemy.getAttack()+randomEnemyDamageDiff;
                            player.setHealth(player.getHealth()-enemyAmtHit);
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK! You've hit the boss for " + specialAmtHit + " damage!");
                            System.out.println("The boss is BLEEDING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss STRIKES in retaliation! You've been hit for " + enemyAmtHit + " damage!");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                            System.out.println("You are now on " + player.getHealth() + "HP");
                        } else if (randomEnemyDiff == 0) {
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            System.out.println("The boss defended but you BROKE his BLOCK!  You've hit the enemy for " + specialAmtHit + " damage!");
                            System.out.println("The boss is BLEEDING!");
                            System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                        } else if (randomEnemyDiff == 1){
                            int enemySpecialHit = enemy.getSpecialAttack() + randomEnemyDamageDiff;
                            if (enemySpecialHit<0)
                                enemySpecialHit = 0;
                            enemy.setHealth(enemy.getHealth()-specialAmtHit);
                            System.out.println("You have successfully used your SPECIAL ATTACK! You've hit the boss for " + specialAmtHit + " damage!");
                            System.out.println("The boss is BLEEDING!");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                            int doesItHit = (int) (Math.random()*3) - 1;
                            if (doesItHit == 0) {
                                player.setHealth(player.getHealth()-enemySpecialHit);
                                System.out.println("The boss SPECIAL STRIKES back and it LANDED! You've been hit for " + enemySpecialHit + " damage!");
                                System.out.println("You are now on " + player.getHealth() + "HP");
                                System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                            } else {
                                System.out.println("The boss SPECIAL STRIKES back but it MISSED!");
                                System.out.println("The boss stands on " + enemy.getHealth() + "HP");
                            }
                        }
                    }
                } else {
                    System.out.println("You've already used your special move!");
                    notCheese = false;
                }
            } else if (choice != 4){
                System.out.println("Not a proper fight option. Please choose again.");
                notCheese = false;
            }
            if (choice == 4) {
                System.out.println("You have fled. Better luck next time!");
                resetPlayer(resetHealth);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (used&&playerClass.equals("Mage")&&notCheese) {
                if (hitCounter<=4) {
                    hitCounter++;
                    enemy.setHealth(enemy.getHealth()-mage.getBurnAttack());
                    System.out.println("\nThe boss has been BURNED for " + mage.getBurnAttack() + "HP");
                    System.out.println("The boss stands on " + enemy.getHealth() + "HP!");
                } else if (hitCounter==5){
                    if (!said) {
                        System.out.println("\nThe burn has worn OFF!");
                        said = true;
                    }
                }
            }
            if (used&&playerClass.equals("Warrior")&&notCheese) {
                if (hitCounter<=1) {
                    hitCounter++;
                    enemy.setHealth(enemy.getHealth()-(warrior.getAttack()/2));
                    System.out.println("\nThe boss bled " + (warrior.getAttack()/2) + "HP");
                    System.out.println("The boss stands on " + enemy.getHealth() + "HP!");
                } else if (hitCounter==2){
                    if (!said) {
                        System.out.println("\nThe wound has healed!");
                        said = true;
                    }
                }
            }
            if (used&&playerClass.equals("Healer")&&notCheese) {
                if (hitCounter<=2) {
                    hitCounter++;
                    player.setHealth(player.getHealth()+(healer.getHeal()/3));
                    System.out.println("\nYour healing essence has healed you for " + (healer.getHeal()/3) + "HP");
                    System.out.println("You are now on " + player.getHealth() + "HP!");
                } else if (hitCounter==3){
                    if (!said) {
                        System.out.println("\nThe healing essence has dispersed!");
                        said = true;
                    }
                }
            }
            if (enemy.getHealth()<=0) {
                enemy = null;
                System.out.println("\nThis.... this can't be........");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println("NOOO!!!!!!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println("\nThe boss is DEFEATED! Well Done!");
                ultBook = "obtained";
                bossDefeated = true;
                System.out.println("\nThe boss dropped a book. It's written in a foreign language..");
                System.out.println("Obtained: Book");
                resetPlayer(resetHealth);
                break;
            } else if (player.getHealth()<=0) {
                System.out.println("\nYou have been DEFEATED. Better luck next time!");
                resetPlayer(resetHealth);
                break;
            }

        }

    }

    private void exploreMoves() {
        if (playerClass.equals("Mage")) {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + mage.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nBurn Attack: Is a recurring attack that will tick every round (UNBLOCKABLE).");
            System.out.println("Your current burn attack stat is " + mage.getBurnAttack());
        } else if (playerClass.equals("Warrior")) {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + warrior.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nSpecial Attack: Is a heavy attack that will heavily damage the opponent (UNBLOCKABLE).");
            System.out.println("Your current special attack stat is " + warrior.getSpecialAttack());
        } else {
            System.out.println("Attack: Is a basic attack that damages the opponent.");
            System.out.println("Your current attack stat is " + healer.getAttack());
            System.out.println("\nDefend: Is a basic move that will defend against the opponent's attack.");
            System.out.println("Your current defend stat is " + player.getDefendStat());
            System.out.println("\nHeal: Is a move that will heal your player (UNBLOCKABLE).");
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
                    System.out.print("Health +1 = 40 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*40) {
                            player.setCoins(player.getCoins()-amtUpg*40);
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
                    System.out.print("Defend +1 = 50 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*50) {
                            player.setCoins(player.getCoins()-amtUpg*50);
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
                    System.out.print("Special Attack +3 = 100 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt()*3;
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
                    System.out.print("Health +1 = 40 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*40) {
                            player.setCoins(player.getCoins()-amtUpg*40);
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
                    System.out.print("Defend +1 = 50 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*50) {
                            player.setCoins(player.getCoins()-amtUpg*50);
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
                    System.out.print("Health +1 = 40 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*40) {
                            player.setCoins(player.getCoins()-amtUpg*40);
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
                    System.out.print("Defend +1 = 50 Coins. How much would you like to upgrade: ");
                    int amtUpg = scan.nextInt();
                    scan.nextLine();
                    if (amtUpg>=0) {
                        if (player.getCoins()>=amtUpg*50) {
                            player.setCoins(player.getCoins()-amtUpg*50);
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
