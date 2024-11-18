public class Warrior {
    private int attack;
    private int specialAttack;

    public Warrior() {
        attack = 2;
        specialAttack = 5;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void upgradeAttack(int amtUpg) {
        attack += amtUpg;
    }

    public void upgradeSpecialAttack(int amtUpg) {
        specialAttack += amtUpg;
    }
}
