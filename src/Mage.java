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

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setBurnAttack(int burnAttack) {
        this.burnAttack = burnAttack;
    }
}
