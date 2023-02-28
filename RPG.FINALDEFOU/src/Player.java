import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private int x;
    private int y;
    private int hp;
    private int dmg;
    private int def;
    private ArrayList<Object> items;

    public Player(int hp, int dmg, int def) {
        this.x = 0;
        this.y = 0;
        this.hp = hp;
        this.dmg = dmg;
        this.def = def;
        this.items = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public ArrayList<Object> getItems() {
        return this.items;
    }
    public void showInventory() {
        System.out.println("Inventaire : ");
        if (this.items.isEmpty()) {
            System.out.println("Votre inventaire est vide");
            return;
        }
        for (Object item : this.items) {
            System.out.println(item);

        }
    }

    public int getDef() {
        return def;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public String move(String m) {
        switch (m){
            case "z", "Z" :{
                if (this.x-- <= 0) this.x--;
                else return "vous avancez d'un pas";
            }
            case "s", "S" :{
                if (this.x++ >= 10) this.x++;
                else return "vous avancez d'un pas";
            }
            case "q", "Q" :{
                if (this.y-- <= 0) this.y--;
                else return "vous avancez d'un pas";
            }
            case "d", "D" :{
                if (this.y++ >= 10) this.y++;
                else return "vous avancez d'un pas";
            }
            default : return "";
        }
    }

    public void grabItem (Item item) {
        this.items.add(item);
        if (item.getClass().equals(ItemHeal.class)) {
            this.hp += item.getStat();
        }
        else if (item.getClass().equals(ItemDammage.class)) {
            this.dmg += item.getStat();
        }
        else if (item.getClass().equals(ItemDef.class)) {
            this.def += item.getStat();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getX() == player.getX() && getY() == player.getY() && getHp() == player.getHp() && getDmg() == player.getDmg() && getDef() == player.getDef() && Objects.equals(getItems(), player.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getHp(), getDmg(), getDef(), getItems());
    }
}

