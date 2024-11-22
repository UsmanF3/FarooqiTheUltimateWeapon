public class Enemy {
    public int attack;
    public int specialAttack;
    public int health;
    public int defend;

    public Enemy() {
        attack = 1;
        defend = 1;
        specialAttack = 3;
        health = 10;
    }

    public Enemy(int level) {
        attack = 1 + level ;
        defend = level;
        specialAttack = 3+level;
        health = 15 + (level*(int)(Math.random()*5)+1);
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
