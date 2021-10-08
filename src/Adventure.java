import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private final Map mapOfGame;
    private final Player player;
    Scanner input = new Scanner(System.in);

    public Adventure(){
        mapOfGame = new Map();
        player = new Player();
        player.setCurrentRoom(mapOfGame.getMap()[0]);

    }

    public void play(){

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

        while(gameActive){

            int currentEnergy = player.getEnergy();
            if(currentEnergy == 50){
                System.out.println("You seem to be tired. I recommend you take a break.");
            }else if(currentEnergy == 15){
                System.out.println("OK, you are officially exhausted. Take a break now or you'll regret it later!");
            }else if(currentEnergy == 0){
                System.out.println("too bad, you have no energy left to complete the adventure game. Told you to take a break...");
                gameActive = false;
            }

            String userInput = input.nextLine().trim().toLowerCase();
               if(userInput.startsWith("go ")){
                userInput = userInput.substring(3);
                energyUpdate(-5);
                System.out.println(player.goTo(userInput));
            }else

                if (userInput.startsWith("exit") || userInput.startsWith("e")){
                System.out.println(Color.BRIGHT_RED + "Leaving already? :(");
                System.out.println("Hopefully we'll see each other again :) ");
                gameActive = false;
            }else

                if (userInput.startsWith("look") || userInput.startsWith("l")) {
                System.out.println(look());
            }else

                if (userInput.startsWith("help") || userInput.startsWith("h")){
                System.out.println(helpPlayer());
            }else

                if (userInput.startsWith("info")){
                System.out.println(player.getInfo());
            }else

                if (userInput.startsWith("take ")){
                userInput = userInput.substring(5);

                Enum<ItemSuccess> success = player.takeItem(userInput);
                //ArrayList<Item> playerItems = player.getItemsInInventory();

                if (success == ItemSuccess.SUCCESS) {
                    System.out.println("You have taken " + userInput);
                }if (success == ItemSuccess.FAIL){
                        System.out.println("This item will exceed your weight limit, " +
                                "please drop an item from your inventory if you wish to take this item.");
                }if (success == ItemSuccess.DOES_NOT_EXIST){
                        System.out.println("You cannot find that item");
                    }//else
                       /* if(userInput.startsWith("eat ")){
                            userInput = userInput.substring(4);
                            Enum<ItemSuccess> success2 = player.takeItem(userInput);

                            if(){

                            }
                        }*/

            }else

                if (userInput.startsWith("drop ")){
                    // cut excess from userInput, in order to simplify commands
                    userInput = userInput.substring(5);

                    // get the item from the item from the players inventory,
                    Item tmpItem = player.getItemFromName(userInput);

                    // drop the item from player inventory, and get statuscode
                    Enum<ItemSuccess> StatusCode = player.dropItem(tmpItem);

                    // depending on the statuscode the user will get the right respons
                    if (StatusCode == ItemSuccess.SUCCESS) {
                        System.out.println("You have dropped " + tmpItem.getShortName());
                    } else if (StatusCode == ItemSuccess.FAIL){
                        System.out.println("That is not possible");
                    } else {
                        System.out.println("I cannot find an item by that name in your inventory");
                    }
            }else

                if (userInput.startsWith("inventory") || userInput.startsWith("inv") || userInput.startsWith("i")) {
                    System.out.println(player.getFormattedInventory());
            }else

                if (userInput.startsWith("inspect ")){
                    userInput = userInput.substring(8);
                    System.out.println(inspect(userInput));
                }else

                    if(userInput.startsWith("break") || userInput.startsWith("b")){
                        takeABreak();
                    } else
                        if(userInput.startsWith("health")){
                            System.out.println(health());
                        }

            else{
                    System.out.println("I don't understand that. Please try again :)");
                }
        }
    }


    public String look(){
        return player.getCurrentRoom().getDescription() + "\n" +
                player.getCurrentRoom().getItems();
    }

    public String inspect(String itemName){
        for (Item item : player.getItemsInInventory()) { // loops through the arrayList itemsInInventory, and set item to the current item
            if (item.getShortName().equalsIgnoreCase(itemName)){ //check if it is in item with the shortName of itemName
                return item.getDescription(); //returns the description of the item.
            }
        }

        return "There is no item by that name in your inventory";
    }

    public String health(){
        return "Health: \t" + player.getHP() + "/" + player.getMaxHP();
    }

    //Updates energyUpdate with 25 points.
    public void takeABreak(){
        energyUpdate(25);
    }

    //Updates player energy
    public void energyUpdate(int energyUpdate){
        int energy = player.getEnergy();
        energy += energyUpdate;
        player.setEnergy(energy);
    }

    public String helpPlayer(){
        return Color.BRIGHT_GREEN + """
                Here is some help for you. Hopefully this will make your journey easier:
                1) To move in and out of different rooms, combine 'go' with a direction,
                   such as north, south, east or west, or simply use the starting letter of the direction.
                2) Type 'look' or 'l', to get a description of the room you are in.
                3) Type 'exit' or 'e', to end the game.
                4) Type 'info', to get player information.
                5) Type 'take', to add an item to your inventory.
                6) Type 'drop', to drop an item from your inventory.
                7) Type 'inventory' or 'inv', to see the list of items that you've collected.
                8) Type 'inspect', to get a description of the item.
                9) Type 'break' or 'b', to take a break.
                I wish you the best of luck!
                XOXO, Gossip girl \uD83D\uDE09
               """;
    }


    public static void main(String[] args) {

        Adventure game = new Adventure();
        game.play();

    }
}
