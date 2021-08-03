package com.intermediate.thegreatrobbery;

import java.util.Scanner;

public abstract class Mechanics {

    private static boolean gameOver;

    public static void setGameOver(boolean gameOver) {
        Mechanics.gameOver = gameOver;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static boolean checkGameOver() {
        byte playersOut = 0;
        for (Criminal player : Assets.getPlayerPool()) {
            if (player.isArrested() || player.hasEscaped()) {
                playersOut++;
                player.hasEscaped();
            }
        }
        return playersOut == Assets.getPlayerPool().length;
    }

    public static void gameInstructions() {
        System.out.println("Welcome to The Great Robbery game!\n" +
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
        "In this game, you play as a criminal trying to rob items from several locations.\n" +
        "But, be careful, detective " + Assets.detective.getName() + ", known to his friends as \n" +
        Assets.detective.getNickname() + ", but I doubt you count among those.\n" +
        "You can attempt as many robberies as you want, but if you get caught, it's GAME OVER! \n" +
        "Each player turn, he's gonna be somewhere at random trying to get you, \n" +
        "if he is at the same place as you, things could become even more difficult.\n" +
        "Each round, you choose a valid place and take a item at random from there or if you had you enough, \n" +
        "you may choose to cut and run!" +
        "There being any other players left in play, they go on for as long as they desire or are able to.\n" +
        "But be careful pushing you luck, as the rounds progress, detective " + Assets.detective.getName() +
        "\n becomes better at tracking you, as well every place increases it's security becoming harder " +
        "to get out with anything. \n" +
        "So, you know... do it at your own risk." + "When all the players have either escaped or been arrested, " +
        "the total value of their loot is counted, whoever has the most WINS!\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Utils.playerCommands();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
        "The game starts now with the first registered player. Good luck to everyone!");
    }

    public static void gameSetup() {
        System.out.println("How many players will be joining this game?");
        Scanner howManyPlayers = new Scanner(System.in);
        byte numberOfPlayers = howManyPlayers.nextByte();

        Assets.setPlayerPool(Utils.createPlayerPool(numberOfPlayers));
        System.out.println(numberOfPlayers + " players will be taking part in tonight's heists!");
        System.out.println("Now let's create each player's character for the game: \n");
        for (byte playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            Criminal character;
            String isCharacterDefault;
            String playerName = Utils.getPlayerName();
            System.out.println(playerName + ", would you like to play a default character (this has purely cosmetic purposes)? " +
                    "'Yes' for default or 'No' for creation: ");
            Scanner promptType = new Scanner(System.in);
            isCharacterDefault = promptType.nextLine().toLowerCase();
            switch (isCharacterDefault) {
                case "yes" -> {
                    character = new Criminal(Utils.createRandomCriminal());
                    Utils.insertCharacterOnPlayerPool(playerName, character, Assets.getPlayerPool(), playerIndex);
                }
                case "no" -> {
                    character = Utils.createCriminal();
                    Utils.insertCharacterOnPlayerPool(playerName, character, Assets.getPlayerPool(), playerIndex);
                }
                default -> System.out.println("This is a invalid option, please try again with 'Yes' or 'No'");
            }
            System.out.println(playerIndex < (numberOfPlayers - 1) ? "\nNext Player: " : "Ok, now we can start the game!");
        }
    }

    public static void gameMatch(Criminal[] playerPool) {
        boolean oneMoreGame = true;
        while (oneMoreGame) {
            if(isGameOver()){
                endGame();
                System.out.println("\n\nThe game is over, would you like to play again with same characters and a new randomized detective? Yes or No:");
                String newGame = new Scanner(System.in).nextLine();
                if ("yes".equals(newGame)) {
                    Utils.clearAll();
                    setGameOver(false);
                } else {
                    oneMoreGame = false;
                    System.out.println("Thank you for playing!");
                    break;
                }
            }
            while (!isGameOver()) {
                System.out.println();
                for (Criminal player : playerPool) {
                    if (player.isArrested()) {
                        System.out.println(player.getName() + " has been caught by " + Assets.detective.getName() +
                                " on a previous round! The next player, if there are any, will play now.");
                    } else if (player.hasEscaped()) {
                        System.out.println(player.getName() + " already got away! Their total loot at the time was: $" + player.getTotalValueForItems());
                    } else {
                        while (true) {
                            System.out.println(player.getPlayerName() + ", choose your action: ");
                            Scanner playerTurn = new Scanner(System.in);
                            String playerChoice = playerTurn.nextLine();
                            if (Utils.isBuildingInCity(playerChoice)) {
                                player.theft(playerChoice);
                                break;
                            } else if (playerChoice.equalsIgnoreCase("Commands")) {
                                Utils.playerCommands();
                            } else if (playerChoice.equalsIgnoreCase("Escape")) {
                                player.runAway();
                                break;
                            } else if (playerChoice.equalsIgnoreCase("Info")) {
                                Utils.securityInfo();
                            } else if (playerChoice.equalsIgnoreCase("Loot")) {
                                player.listHaulItems();
                            } else if (playerChoice.equalsIgnoreCase("Loot all")) {
                                Utils.getHaulForAllCriminals();
                            } else if (playerChoice.equalsIgnoreCase("Bio")) {
                                player.printBioData();
                            } else if (playerChoice.equalsIgnoreCase("Law loot")) {
                                if (Assets.detective.getTotalValueForItems() < 1) {
                                    System.out.println("No one has been apprehended yet, so no items have been recovered by the law.");
                                } else {
                                    System.out.println("The law has recovered $" + Assets.detective.getTotalValueForItems() + " in stolen items.");
                                }
                            } else if (playerChoice.equalsIgnoreCase("Law bio")) {
                                Assets.detective.printBioData();
                            } else {
                                System.out.println("This is a invalid command. To see all commands available, type 'Commands'.");
                            }
                        }

                    }
                }
                City.increaseSecurity();
                setGameOver(checkGameOver());
            }
        }

    }

        public static void endGame() {
            String winner = null;
            short detectiveHaul = Assets.detective.getTotalValueForItems();
            short criminalLargestHaul = 0;
            short criminalTotalHaul = 0;

            for (Criminal player : Assets.getPlayerPool()) {
                if (player.isArrested()) {
                    System.out.println(player.getPlayerName() + "'s character was arrested before escaping. Their total haul at the time was: $" + player.getTotalValueForItems());
                } else {
                    if (player.getTotalValueForItems() > criminalLargestHaul) {
                        criminalLargestHaul = player.getTotalValueForItems();
                        winner = player.getPlayerName() + " is the winner with the biggest haul!";
                    }
                    criminalTotalHaul += player.getTotalValueForItems();
                    System.out.println(player.getPlayerName() + "'s total haul was: $" + player.getTotalValueForItems());
                }
            }
            if (detectiveHaul > criminalTotalHaul) {
                winner = "Detective " + Assets.detective.getName() + " recovered: $" + detectiveHaul + ", which is more than the total stolen by the criminals combined, making the law win the match!";
            }
            System.out.println("-----------------------------");
            System.out.println(winner);
        }
    }