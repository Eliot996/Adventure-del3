import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private final ArrayList<Enemy> enemies = new ArrayList();
    private final Map mapOfGame;
    private final Player player;
    Scanner input = new Scanner(System.in);

    public Adventure() {
        mapOfGame = new Map();
        player = new Player();
        player.setCurrentRoom(mapOfGame.getMap()[0]);
        enemies.add(new Enemy("orc", 11,
                new MeleeWeapon("sword", "a sword", "swordDesc", 10, 10),
                mapOfGame.getMap()[0]));
    }

    public void play() {

        boolean gameActive = true;
        //Intro to the game.
        System.out.println(Color.VIBRANT_PURPLE + "Welcome to the adventure game!" +
                "\nThere are many different rooms to travel between, some rooms are better than others," +
                " but your main goal should be to find the 'the vault'. " +
                "\nIf you at any point need assistance, just type 'help' or ´h', where a useful guide will magically appear" +
                "\n I wish you the best of luck! See you on the other side \uD83D\uDE09");
        System.out.println();
        System.out.println(Color.RESET_COLOR + "You are currently in " + player.getCurrentRoom().getDescription());
        System.out.println("Which direction would you like to go?");

        while (gameActive) {

            int currentEnergy = player.getEnergy();
            if (currentEnergy == 50) {
                System.out.println("You seem to be tired. I recommend you take a break.");
            } else if (currentEnergy == 15) {
                System.out.println("OK, you are officially exhausted. Take a break now or you'll regret it later!");
            } else if (currentEnergy == 0) {
                System.out.println("too bad, you have no energy left to complete the adventure game. Told you to take a break...");
                gameActive = false;
            }

            String userInput = input.nextLine().trim().toLowerCase();

            if (userInput.startsWith("go ")) {
                userInput = userInput.substring(3);
                player.energyUpdate(-5);
                System.out.println(player.goTo(userInput));

            } else if (userInput.startsWith("exit")){
                System.out.println(Color.BRIGHT_RED + "Leaving already? :(");
                System.out.println("Hopefully we'll see each other again :) ");
                gameActive = false;

            } else if (userInput.startsWith("look") || userInput.startsWith("l")) {
                System.out.println(look());

            }else if (userInput.startsWith("help")){
                System.out.println(helpPlayer());

            } else if (userInput.startsWith("info")) {
                System.out.println(player.getInfo());

            } else if (userInput.startsWith("take ")) {
                // cut excess from userInput, in order to simplify commands
                userInput = userInput.substring(5);

                // get the item from the room the player is currently in
                Item tmpItem = player.getCurrentRoom().getItemFromName(userInput);

                // take item and get StatusCode
                Enum<StatusCode> statusCode = player.takeItem(tmpItem);

                    // depending on the statuscode the user will get the right respons
                    if (statusCode == StatusCode.SUCCESS) {
                        System.out.println("You have taken " + tmpItem.getShortName());
                    }else if (statusCode == StatusCode.FAIL){
                        System.out.println("This item will exceed your weight limit, " +
                                "please drop an item from your inventory if you wish to take this item.");
                    }else if (statusCode == StatusCode.DOES_NOT_EXIST){
                        System.out.println("You cannot find that item");
                    }

                }else if(userInput.startsWith("eat ")){

                    userInput = userInput.substring(4);
                    Item tempItem = player.getItemFromName(userInput);
                    Enum<StatusCode> success2 = player.eatItem(tempItem);

                    if(success2 == StatusCode.SUCCESS){
                        System.out.println("you have eaten " + tempItem.getShortName());
                    }else if(success2 == StatusCode.FAIL){
                        System.out.println("You cannot eat that item");
                    }else if(success2 == StatusCode.DOES_NOT_EXIST){
                        System.out.println("That item does not exist");
                    }
                }
                else if (userInput.startsWith("drop ")) {
                // cut excess from userInput, in order to simplify commands
                userInput = userInput.substring(5);

                // get the item from the players inventory,
                Item tmpItem = player.getItemFromName(userInput);

                // drop the item from player inventory, and get statuscode
                Enum<StatusCode> statusCode = player.dropItem(tmpItem);

                // depending on the statuscode the user will get the right respons
                if (statusCode == StatusCode.SUCCESS) {
                    System.out.println("You have dropped " + tmpItem.getShortName());
                } else if (statusCode == StatusCode.FAIL) {
                    System.out.println("That is not possible");
                } else if (statusCode == StatusCode.DOES_NOT_EXIST) {
                    System.out.println("I cannot find an item by that name in your inventory");
                }

            } else if (userInput.startsWith("inventory") || userInput.startsWith("inv") || userInput.startsWith("i")) {
                System.out.println(player.getFormattedInventory());

            } else if (userInput.startsWith("inspect ")) {
                userInput = userInput.substring(8);
                System.out.println(inspect(userInput));

            } else if (userInput.startsWith("break") || userInput.startsWith("b")) { // TODO: 11/10/2021 add message
                player.takeABreak();

            } else if (userInput.startsWith("health")) {
                System.out.println(player.health());

            }else if (userInput.startsWith("attack ")) { // TODO: 08/10/2021 add functionality to command
                userInput = userInput.substring(7);

                Enemy tempEnemy = getEnemyFromName(userInput);
                if (tempEnemy != null){
                    player.attack(tempEnemy);
                    System.out.println("enemy health: "+ tempEnemy.getHealth());
                }

            } else if (userInput.startsWith("equip ")) {
                // cut excess from userInput, in order to simplify commands
                userInput = userInput.substring(6);

                // get the item from the room the player is currently in
                Item tmpItem = player.getItemFromName(userInput);

                // take item and get StatusCode
                Enum<StatusCode> statusCode = player.equipWeapon(tmpItem);

                // depending on the statuscode the user will get the right respons
                if (statusCode == StatusCode.SUCCESS) {
                    System.out.println("You have equipped " + tmpItem.getShortName());
                }else if (statusCode == StatusCode.FAIL){
                    System.out.println("That item is not a weapon");
                }else if (statusCode == StatusCode.SLOT_NOT_EMPTY){
                    System.out.println("You already have an item equipped");
                }else if (statusCode == StatusCode.DOES_NOT_EXIST){
                    System.out.println("You cannot find an item by that name");
                }

            } else if (userInput.startsWith("unequip weapon")) {
                Enum<StatusCode> statusCodeEnum = player.unEquipWeapon();

                if (statusCodeEnum == StatusCode.SUCCESS){
                    System.out.println("You have unequipped your weapon");
                }else if (statusCodeEnum == StatusCode.FAIL){
                    System.out.println("You dont have a weapon equipped");
                }

            }else{
                System.out.println("I don't understand that. Please try again :)");
            }
        }
    }


    public String look() { // TODO: 08/10/2021 refactor
        StringBuilder returnString = new StringBuilder(player.getCurrentRoom().getDescription() + "\n" +
                player.getCurrentRoom().getItems());

        for (Enemy enemy : enemies) {
            if (enemy.getCurrentRoom() == player.getCurrentRoom()) {
                returnString.append("\nYou also see a ").append(enemy.getName()).append(" in the room");
            }
        }
        return returnString.toString();
    }

    public String inspect(String itemName) {
        for (Item item : player.getItemsInInventory()) { // loops through the arrayList itemsInInventory, and set item to the current item
            if (item.getShortName().equalsIgnoreCase(itemName)) { //check if it is in item with the shortName of itemName
                return item.getDescription(); //returns the description of the item.
            }
        }

        return "There is no item by that name in your inventory";
    }

    public String helpPlayer() { // TODO: 08/10/2021 update to include new commands
        return Color.BRIGHT_GREEN + """
                 Here is some help for you. Hopefully this will make your journey easier:
                 1) To move in and out of different rooms, combine 'go' with a direction,
                    such as north, south, east or west, or simply use the starting letter of the direction.
                 2) Type 'look' or 'l', to get a description of the room you are in.
                 3) Type 'exit', to end the game.
                 4) Type 'info', to get player information.
                 5) Type 'take', to add an item to your inventory.
                 6) Type 'drop', to drop an item from your inventory.
                 7) Type 'inventory' or 'inv', to see the list of items that you've collected.
                 8) Type 'inspect', to get a description of the item.
                 9) Type 'break' or 'b', to take a break.
                 10) Type 'health', to see your health information.
                 11) Type 'attack', to attack to attack enemies.
                 I wish you the best of luck!
                 XOXO, Gossip girl \uD83D\uDE09
                """;
    }

    private Enemy getEnemyFromName(String enemyName){
        for (Enemy enemy : enemies) {
            if (enemy.getName().equalsIgnoreCase(enemyName)){
                return enemy;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        Adventure game = new Adventure();
        game.play();

    }
}
