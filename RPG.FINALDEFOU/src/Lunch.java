import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Lunch {
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(ANSI_YELLOW + "Bienvenue dans le mini RPG adventure !" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_YELLOW + "Vous êtes Bob un aventurier venue combattre le méchant dragon qui térrorise les habitants de Abys" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Vous n'avez pas vraiment de pouvoir magique mais une bravour et une ardeur à toute épreuve !" + ANSI_RESET);
        System.out.println();
        System.out.println("Statistiques :");
        System.out.println(ANSI_BLUE + "(10) HP" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "(2) ATK" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "(0) DEF" + ANSI_RESET);
        System.out.println();

        Game game = new Game();
        game.createMap();
        game.addThings();
        game.printMap();
        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println(ANSI_YELLOW + "Que voulez-vous faire ? " + ANSI_RESET);
        System.out.println("z. Aller en haut");
        System.out.println("q. Aller en bas");
        System.out.println("s. Aller à gauche");
        System.out.println("d. Aller à droite");
        System.out.println("1. Afficher l'inventaire");
        System.out.print(">>");


        String in = "";
        boolean play = true;
        while (play) {
            in = scanner.next();
            if (Objects.equals(in, "1")){
                game.getPlayer().showInventory();

            }
            Object o = game.isTouching();
            if (o == null) {
                System.out.println(game.playerMove(in));
            } else { // un truc est à porter
                System.out.println(ANSI_YELLOW + "Oh mais vous avez trouvé quelque chose !" + ANSI_RESET);
                if (Ennemy.class.equals(o.getClass())) {
                    if (fight(game, scanner)) {
                        System.out.println(ANSI_GREEN + "Vous avez vaincu le dragon !!, les habitants de Abys vous remercient et vous offrent une pipe chacun !" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_PURPLE + "Vous avez perdu face au terrible dragon" + ANSI_RED + "X-X" + ANSI_GREEN +" vous êtes mort et vous n'avez même pas de pipe pour vous consoler !" + ANSI_RESET);
                    }
                    play = false;
                } else {
                    System.out.println(ItemHeal.class.equals(o.getClass()));

                    if (ItemHeal.class.equals(o.getClass())) { //pour ramasser un item de soins
                        System.out.println(ANSI_GREEN + "Vous avez trouvé une potion !" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Cette potion vous permet de restaurez 3 HP !, Faite en bon usage !" + ANSI_RESET);

                    } else if (ItemDammage.class.equals(o.getClass())) { //pour ramasser un item de degats


                        System.out.println(ANSI_GREEN + "Vous avez trouvé une MasterSword!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Cette épée vous permet d'infliger 2 points de dégats supplémentaire" + ANSI_RESET);
                        System.out.println();
                        System.out.println("Statistique Mise à jour :");
                        System.out.println(ANSI_BLUE + "(10) HP" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "(4) ATK" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "(0) DEF" + ANSI_RESET);

                    } else if (ItemDef.class.equals(o.getClass())) { //pour ramasser un item de defense

                        System.out.println(ANSI_GREEN + "Vous avez trouvé un Shield Divin!" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Ce bouclier vous permet réduire les degats du dragon contre vous" + ANSI_RESET);
                        System.out.println();
                        System.out.println("Statistique Mise à jour :");
                        System.out.println(ANSI_BLUE + "(10) HP" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "(4) ATK" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "(2) DEF" + ANSI_RESET);
                    }
                    game.getPlayer().grabItem((Item) o);
                    o = null;
                }
                System.out.println();
                System.out.println(ANSI_RED + "Vous avez " + game.getPlayer().getHp() + " HP" + ANSI_RESET); //afficher les points de vie du joueur
                System.out.println(game.getPlayer().getItems());
            }
            game.printMap();
            System.out.print(">>");
        }
    }
    private static boolean fight(Game game, Scanner scanner) {
        //bla bla pour fumer un ennemy avec un switch
        System.out.println( ANSI_PURPLE + "Malheur c'est Le dragon Abyssal, il vous attaque !" + ANSI_RESET);
        Random rand = new Random();
        System.out.println( ANSI_PURPLE + "le dragon vous soulève et vous met un ultralaser dans votre gueule" + ANSI_RESET);
        game.getPlayer().setHp(game.getPlayer().getHp() - rand.nextInt(3));
        System.out.println(ANSI_RED + "Il vous reste " + game.getPlayer().getHp() + " HP" + ANSI_RESET);
        while (true) {
            if (game.getPlayer().getItems().contains(game.getShield())) {
                System.out.println("""
                        1 - proteger avec votre bouclier vous tel le moine shaoling
                        2 - TABASSE LE DRAGON avec t'es petits poings
                        """);
                System.out.print(">>");
                int choix = scanner.nextInt();
                if (choix == 1) {
                    System.out.println(ANSI_BLUE + "Vous vous protegez avec votre Bouclier Divin et réduisez les dégats du dragon" + ANSI_RESET);
                    game.getPlayer().setDef(3);
                    game.getPlayer().setHp(game.getPlayer().getHp() - rand.nextInt(1));
                } else if (choix == 2) {
                    if (game.getPlayer().getItems().contains(game.getSword())) {
                        System.out.println(ANSI_RED + "Vous attaquez le dragon avec votre MasterSword" + ANSI_RESET);
                        game.getDragon().setHp(game.getDragon().getHp() - game.getPlayer().getDmg());
                        System.out.println(" Le dragon possède encore " + game.getDragon().getHp() + "HP");
                    } else {
                        System.out.println("Vous attaquez le dragon avec votre petit poing tout flasque");
                        game.getDragon().setHp(game.getDragon().getHp() - 1);
                        System.out.println(ANSI_PURPLE + " Le dragon possède encore " + game.getDragon().getHp() + "HP" + ANSI_RESET);
                    }
                }
            } else if (game.getPlayer().getItems().contains(game.getSword())) {
                System.out.println("Vous attaquez le dragon avec votre MasterSword");
                game.getDragon().setHp(game.getDragon().getHp() - game.getPlayer().getDmg());
            } else {
                System.out.println("""
                        1 - gogo gadjeto poing dans la gueule du dragon 
                        2 - sort ta bite et met un coup de queue ")
                        """);
                System.out.print(">>");
                int choix = scanner.nextInt();
                game.getDragon().setHp(game.getDragon().getHp() - 1);
                System.out.println(ANSI_YELLOW + "INCROYABLE, vous avez réussi à le toucher et lui avez infliger 1 de dégats" + ANSI_RESET);
                System.out.println(ANSI_PURPLE + " Le dragon possède encore " + game.getDragon().getHp() + "HP" + ANSI_RESET);
            }
            System.out.println( ANSI_PURPLE + "le dragon vous soulève et vous met un ultralaser dans votre gueule" + ANSI_RESET);
            game.getPlayer().setHp(game.getPlayer().getHp() - rand.nextInt(3));
            System.out.println(ANSI_RED + "Il vous reste " + game.getPlayer().getHp() + " HP" + ANSI_RESET);
            if (game.getPlayer().getHp() <= 0) return false;
            else if (game.getDragon().getHp() <= 0)
                return true;
        }
    }
}

