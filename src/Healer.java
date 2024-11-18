public class Healer {
    private int attack;
    private int heal;

    public Healer() {
        attack = 2;
        heal = 3;
    }

    public int getAttack() {
        return attack;
    }

    public int getHeal() {
        return heal;
    }

    public void upgradeAttack(int amtUpg) {
        attack += amtUpg;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
