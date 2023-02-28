import java.util.Scanner;

import static com.sun.java.accessibility.util.EventID.ITEM;

public class RPG {
    private static final int MAP_WIDTH = 5;
    private static final int MAP_HEIGHT = 5;
    private static final char EMPTY_SPACE = '.';
    private static final char PLAYER_CHAR = 'J';
    private static final char ENEMY_CHAR = 'E';
    private static final char ITEM_CHAR = 'I';
    private static final char SWORD_CHAR = 'S';
    private static final char POTION_CHAR = 'P';
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";



    private static int playerX = 0;
    private static int playerY = 0;
    private static boolean hasSword = false;
    private static boolean hasPotion = false;
    private static int playerHealth = 10;
    private static int playerDamage = 2;
    private static int enemyX = 4;
    private static int enemyY = 4;
    private static int enemyHealth = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;
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

        while (playing) {
            System.out.println("Carte : ");
            printMap();
            System.out.println();
            System.out.println(ANSI_RED + "Points de vie restants : " + playerHealth + ANSI_RESET);
            System.out.println();
            System.out.println(ANSI_YELLOW + "Déplacement" + ANSI_RESET);
            System.out.println("1. Aller en haut");
            System.out.println("2. Aller en bas");
            System.out.println("3. Aller à gauche");
            System.out.println("4. Aller à droite");
            System.out.println();
            System.out.println(ANSI_YELLOW + "Action" + ANSI_RESET);
            System.out.println("5. Attaquer l'ennemi");
            System.out.println("6. Utiliser une potion");
            System.out.println("i. Ouvrir l'inventaire");
            System.out.println("q. Quitter");
            System.out.println();

            System.out.println(ANSI_YELLOW + "Que voulez-vous faire ? " + ANSI_RESET);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    movePlayer(0, -1);
                    break;
                case "2":
                    movePlayer(0, 1);
                    break;
                case "3":
                    movePlayer(-1, 0);
                    break;
                case "4":
                    movePlayer(1, 0);
                    break;
                case "5":
                    attackEnemy();
                    break;
                case "6":
                    usePotion();
                    break;
                case "i":
                    showInventory();
                    break;
                case "q":
                    playing = false;
                    break;
                default:
                    System.out.println(ANSI_RED + "ERREUR   " + ANSI_RESET + "Commande invalide !");
                    System.out.println();
                    break;
            }
            if (playerHealth <= 0) {
                System.out.println(ANSI_RED + "YOU ARE DEAD !" + ANSI_RESET);
                playing = false;
            } else if (enemyHealth <= 0) {
                System.out.println(ANSI_GREEN + "Félicitation vous avez vaincu le dragon Abyssal et sauvé le peuple d'abys,  la princesse vous offrira une pipe !" + ANSI_RESET);
                playing = false;
            }
        }
    }

    private static void printMap() {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print(PLAYER_CHAR);
                } else if (x == enemyX && y == enemyY) {
                    System.out.print(ENEMY_CHAR);
                } else if (x == 2 && y == 2 && !hasSword) {
                    System.out.print(ITEM_CHAR);
                } else if (x == 3 && y == 3 && !hasPotion) {
                    System.out.print(ITEM);
                } else if (x == 2 && y == 3 && hasSword) {
                    System.out.print(SWORD_CHAR);
                } else if (x == 3 && y == 2 && hasPotion) {
                    System.out.print(POTION_CHAR);
                } else {
                    System.out.print(EMPTY_SPACE);
                }
            }
            System.out.println();
        }
    }

    private static void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX < 0 || newX >= MAP_WIDTH || newY < 0 || newY >= MAP_HEIGHT) {
            System.out.println(ANSI_RED + "Vous ne pouvez pas sortir de la carte, vous êtes soit aveugles soit un bouffon !" + ANSI_RESET);
        } else {
            playerX = newX;
            playerY = newY;
            checkForItems();
            checkForEnemy();
        }
    }

    private static void checkForItems() {
        if (playerX == 2 && playerY == 2 && !hasSword) {
            System.out.println(ANSI_GREEN + "Vous avez trouvé une MasterSword!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Cette épée vous permet d'infliger 2 points de dégats supplémentaire" + ANSI_RESET);
            System.out.println();
            System.out.println("Statistique Mise à jour :");
            System.out.println(ANSI_BLUE + "(10) HP" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "(4) ATK" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "(0) DEF" + ANSI_RESET);

            hasSword = true;
            playerDamage += 2;
        } else if (playerX == 3 && playerY == 3 && !hasPotion) {
            System.out.println(ANSI_GREEN + "Vous avez trouvé une potion !" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Cette potion vous permet de restaurez 5 HP !, Faite en bon usage !" + ANSI_RESET);
            hasPotion = true;
        }
    }

    private static void checkForEnemy() {
        if (playerX == enemyX && playerY == enemyY) {
            System.out.println(ANSI_PURPLE + "Vous êtes face au terrible dragon Abyssal !" + ANSI_RESET);
        }
    }

    private static void attackEnemy() {
        if (playerX == enemyX && playerY == enemyY) {
            System.out.println(ANSI_YELLOW + "Go, go gadgeto poing dans sa gueule !" + ANSI_RESET);
            int damage = playerDamage;
            if (hasSword) {
                damage += 2;
            }
            enemyHealth -= damage;
            if (enemyHealth > 0) {
                System.out.println(ANSI_RED + "Le dragon Abyssal a " + ANSI_RED + enemyHealth + " points de vie restants." + ANSI_RESET);

                System.out.println(ANSI_PURPLE + "Le dragon Abyssal utilise Souffle du néant Eternel" + ANSI_RESET);
                playerHealth--;
            }
        } else {
            System.out.println(ANSI_RED + "Il n'y a pas d'ennemi à attaquer abrutit!" + ANSI_RESET );
            System.out.println();
        }
    }

    private static void usePotion() {
        if (hasPotion) {
            System.out.println(ANSI_GREEN + "Vous utilisez une potion et récupérer 5 hp!" + ANSI_RESET );
            playerHealth += 5;
            if (playerHealth > 10) {
                playerHealth = 10;
            }
            hasPotion = false;
        } else {
            System.out.println(ANSI_RED + "Vous n'avez pas de potion !" + ANSI_RESET );
        }
    }

    private static void showInventory() {
        System.out.println(ANSI_YELLOW + "Équipement :" + ANSI_RESET );
        if (hasSword) {
            System.out.println(" - Épée");
        }
        System.out.println("Potions : " + (hasPotion ? "1" : "0"));
    }
}

