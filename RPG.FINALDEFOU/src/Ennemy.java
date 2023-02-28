import java.util.Objects;
import java.util.Random;

public class Ennemy {

    private int hp;
    private int dmg;
    private int x;
    private int y;

    public Ennemy(int hp, int dmg) {
        this.hp = hp;
        this.dmg = dmg;
        this.x =  new Random().nextInt(9);
        this.y =  new Random().nextInt(9);
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ennemy ennemy)) return false;
        return getHp() == ennemy.getHp() && getDmg() == ennemy.getDmg() && getX() == ennemy.getX() && getY() == ennemy.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHp(), getDmg(), getX(), getY());
    }
}
