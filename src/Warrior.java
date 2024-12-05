public class Warrior {
    private int attack;
    private int specialAttack;
    private Player player;

    public Warrior() {
        attack = 2;
        specialAttack = 4;
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

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void upgradeAttack(int amtUpg) {
        attack += amtUpg;
    }

    public void upgradeSpecialAttack(int amtUpg) {
        specialAttack += amtUpg;
    }
}
