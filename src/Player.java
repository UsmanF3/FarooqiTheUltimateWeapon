public class Player {
    private String playerClass;
    private int coins;
    private String name;

    public Player(String playerClass, String name) {
        this.playerClass = playerClass;
        coins = 10;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
