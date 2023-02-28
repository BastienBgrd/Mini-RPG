import java.util.Objects;
import java.util.Random;

public class Chest {

    private int x;
    private int y;

    private Random rand = new Random();

    public Chest() {
        super();
    }

    public int getStat() {
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chest chest)) return false;
        return getStat() == chest.getStat() && getX() == chest.getX() && getY() == chest.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStat(), getX(), getY());
    }
}

