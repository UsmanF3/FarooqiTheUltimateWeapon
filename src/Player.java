public class Player {
    private String playerClass;
    private int coins;
    private int health;
    private String name;
    private Mage mage;
    private Warrior warrior;
    private Healer healer;

    public Player(String playerClass, String name) {
        this.playerClass = playerClass;
        coins = 10000;
        health = 20;
        this.name = name;
        if (playerClass.equals("Mage")) {
            mage = new Mage();
        } else if (playerClass.equals("Healer")) {
            healer = new Healer();
        } else if (playerClass.equals("Warrior")) {
            warrior = new Warrior();
        }
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Healer getHealer() {
        return healer;
    }

    public Mage getMage() {
        return mage;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setName(String name) {
        this.name = name;
    }
}
