import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Game {
    private Player player;
    private Ennemy dragon;
    private final ItemDammage sword;
    private final ItemHeal potion;
    private final ItemDef shield;
    private final Chest gold_Chest;

    private final ArrayList<ArrayList<String>> map;

    public Game() {
        this.player = new Player(10, 2, 0);
        this.dragon = new Ennemy(8, 3);
        this.sword = new ItemDammage(2);
        this.potion = new ItemHeal(3);
        this.shield = new ItemDef(2);
        this.map = new ArrayList<>();
        this.gold_Chest = new Chest();
    }



    public void createMap() {
        for (int i = 0; i < 10; i++) {
            ArrayList<String> mapI = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                mapI.add(" ");
            }
            this.map.add(mapI);
        }
    }

    public void createRandPos() {
        Random rand = new Random();
        sword.setX(rand.nextInt(9));
        sword.setY(rand.nextInt(9));
        potion.setX(rand.nextInt(9));
        potion.setY(rand.nextInt(9));
        shield.setX(rand.nextInt(9));
        shield.setY(rand.nextInt(9));
        gold_Chest.setX(rand.nextInt(9));
        gold_Chest.setY(rand.nextInt(9));
    }

    public void addThings() {
        createRandPos();
        this.map.get(this.player.getX()).set(this.player.getY(), "P");
        this.map.get(this.dragon.getX()).set(this.dragon.getY(), "D");
        this.map.get(this.sword.getX()).set(this.sword.getY(), "M");
        this.map.get(this.potion.getX()).set(this.potion.getY(), "H");
        this.map.get(this.shield.getX()).set(this.shield.getY(), "S");
        this.map.get(this.gold_Chest.getX()).set(this.gold_Chest.getY(), "C");
    }

    public void printMap() {
        this.map.get(this.player.getX()).set(this.player.getY(), "P");
        this.map.get(this.dragon.getX()).set(this.dragon.getY(), "D");
        this.map.forEach(System.out::println);
    }

    public String playerMove(String m) {
        this.map.get(this.player.getX()).set(this.player.getY(), "-");
        this.map.get(this.dragon.getX()).set(this.dragon.getY(), "-");
        return this.player.move(m);
    }

    public Chest getGold_Chest() {
        return gold_Chest;
    }
    public Player getPlayer() {
        return player;
    }

    public Ennemy getDragon() {
        return dragon;
    }

    public ArrayList<ArrayList<String>> getMap() {
        return map;
    }

    public ItemDammage getSword() {
        return sword;
    }

    public ItemHeal getPotion() {
        return potion;
    }

    public ItemDef getShield() {
        return shield;
    }

    public Object isTouching() {
        int index;
        String pos;

        index = this.player.getX() + 1;
        if ((index >= 0) && (index < 10)) { //check if the index is out of bounds
            pos = this.map.get(this.player.getX() + 1).get(player.getY());
            if (!Objects.equals(pos, " ")) //check if the string is smth
                return wtfImTouching(pos);
        }

        index = this.player.getX() - 1;
        if ((index >= 0) && (index < 10)) {
            pos = this.map.get(this.player.getX() - 1).get(player.getY());
            if (!Objects.equals(pos, " "))
                return wtfImTouching(pos);
        }

        index = this.player.getY() + 1;
        if ((index >= 0) && (index < 10)) {
            pos = this.map.get(this.player.getX()).get(player.getY() + 1);
            if (!Objects.equals(pos, " "))
                return wtfImTouching(pos);
        }

        index = this.player.getY() - 1;
        if ((index >= 0) && (index < 10)) {
            pos = this.map.get(this.player.getX()).get(player.getY() - 1);
            if (!Objects.equals(pos, " "))
                return wtfImTouching(pos);
        }
        return null;
    }

    public Object wtfImTouching(String s) {
            switch (s) {
            case "D" ->{
                this.map.get(this.dragon.getX()).set(this.dragon.getY(), " ");
                return this.dragon;
            }
            case "M" -> {
                this.map.get(this.sword.getX()).set(this.sword.getY(), " ");
                return this.sword;
            }
            case "H" -> {
                this.map.get(this.potion.getX()).set(this.potion.getY(), " ");
                return this.potion;
            }
            case "S" -> {
                this.map.get(this.shield.getX()).set(this.shield.getY(), " ");
                return this.shield;
            }
                default -> {
                    return null;
                }
            }

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Game game)) return false;
        if (!super.equals(object)) return false;
        return java.util.Objects.equals(getPlayer(), game.getPlayer()) && java.util.Objects.equals(getDragon(), game.getDragon()) && java.util.Objects.equals(sword, game.sword) && java.util.Objects.equals(potion, game.potion) && java.util.Objects.equals(shield, game.shield) && java.util.Objects.equals(getMap(), game.getMap());
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), getPlayer(), getDragon(), sword, potion, shield, getMap());
    }
}
