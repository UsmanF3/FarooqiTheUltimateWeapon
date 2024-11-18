public class Mage {
    private int attack;
    private int burnAttack;

    public Mage() {
        attack = 2;
        burnAttack = 1;
    }

    public int getAttack() {
        return attack;
    }

    public int getBurnAttack() {
        return burnAttack;
    }

    public void upgradeAttack(int amtUpg) {
        attack += amtUpg;
    }

    public void upgradeBurnAttack(int amtUpg) {
        burnAttack += amtUpg;
    }
}
