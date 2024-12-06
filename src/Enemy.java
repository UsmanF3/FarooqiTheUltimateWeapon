public class Enemy {
    private int attack;
    private int specialAttack;
    private int health;
    private int defend;

    public Enemy() {
        attack = 1;
        defend = 1;
        specialAttack = 3;
        health = 10;
    }

    public Enemy(int level) {
        attack = 2 + level ;
        defend = 1+level;
        specialAttack = 5+level;
        health = 15 + (level*(int)(Math.random()*10)+1);
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
