public class Enemy {
    private int attack;
    private int specialAttack;
    private int health;
    private int defend;

    public Enemy(String boss) {
        attack = 15;
        defend = 15;
        specialAttack = 40;
        health = 350;
    }

    public Enemy(int level) {
        attack = 2 + level;
        defend = 1+level;
        specialAttack = 5+level;
        health = 20 + (level*(int)(Math.random()*8)+1);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefend() {
        return defend;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }
}
