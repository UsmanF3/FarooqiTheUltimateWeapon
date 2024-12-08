public class Healer {
    private int attack;
    private int heal;
    private Player player;

    public Healer() {
        attack = 2;
        heal = 3;
    }

    public Healer(int attack) {
        this.attack = attack;
        heal = 3;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
