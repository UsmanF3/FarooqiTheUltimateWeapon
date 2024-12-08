public class Mage {
    private int attack;
    private int burnAttack;
    private Player player;

    public Mage() {
        attack = 2;
        burnAttack = 1;
    }

    public Mage(int attack) {
        this.attack = attack;
        burnAttack = 1;
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getBurnAttack() {
        return burnAttack;
    }

    public void setBurnAttack(int burnAttack) {
        this.burnAttack = burnAttack;
    }

    public void setBurnAttack() {
        burnAttack++;
    }
}
