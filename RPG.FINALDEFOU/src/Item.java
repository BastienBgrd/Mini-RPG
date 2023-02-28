import java.util.Objects;

public class Item {

    private int stat = 0;
    private int x;
    private int y;


    public Item() {
        this.stat = stat;

    }

    public int getStat() {
        return this.stat;
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
        if (!(o instanceof Item item)) return false;
        return getStat() == item.getStat() && getX() == item.getX() && getY() == item.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStat(), getX(), getY());
    }
}
