package com.intermediate.thegreatrobbery;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Utils {

    public static short itemRangeValue(int min, int max) {
        int range = (max - min) + 1;
        return (short) (Math.random() * range + min);
    }

    public static short rangeFromZero(short max) {
        return (short) (Math.random() * max);
    }

    public static void securityInfo() {
        String nameCorrected;
        for (Building building : Assets.city.getCityBuildings()) {
            System.out.println("Location: " + building.getName());
            System.out.println("Security check: " + Utils.twoDecimals(building.getSecurityPercentage()) + "%");
            System.out.println("Security increased per round: " + Utils.twoDecimals(building.getSecurityIncreasePerRound()) + "%");
            for (Item item : building.getItems()) {
                if (item.getNAME().equals("nothing")) {
                    continue;
                }
                nameCorrected = item.getNAME().startsWith("a ") ? item.getNAME().replace("a ", "") : item.getNAME().replace("the ", "");
                System.out.println("Item: " + nameCorrected + ", Value range: " + item.getVALUEMIN() + " ~ " + item.getVALUEMAX());
            }
            System.out.println("--------------------------------------------");
        }
        System.out.println("Detective " + Assets.detective.getName() + " current security check is: " + Assets.detective.getSuccessPercentage() + "%.");
    }

    public static void playerCommands() {
        System.out.println("These are the valid inputs during your turn: ");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Commands -> List all valid commands");
        System.out.println("Bank, Mansion, Post Office, Supermarket -> Valid robbery places.");
        System.out.println("Info -> Returns all locations, their respective items, value ranges, and current security check.");
        System.out.println("Loot -> List all your stolen items and the current total value for them.");
        System.out.println("Law loot -> Shows the total value the detective and locations have recovered at this moment.");
        System.out.println("Escape -> Leave the current game and wait for the other players.");



    }

    public static boolean isBuildingInCity(String playerPrompt) {
        boolean buildingFound = false;
        for (Building building : Assets.getCityBuildings())
            if (playerPrompt.equals(building.getName().toLowerCase())) {
                buildingFound = true;
                break;
            }
        return buildingFound;
    }

    public static Criminal[] createPlayerPool(byte playerCount) {
        while (playerCount > 4 || playerCount < 1) {
            System.out.println("The number of players must be between 1 and 4. Choose again: ");
            Scanner prompt = new Scanner(System.in);
            playerCount = prompt.nextByte();
        }
        return new Criminal[playerCount];
    }

    public static void insertCharacterOnPlayerPool(String playerName, Criminal character, Criminal[] playersOnMatch, byte playerIndex) {
        character.setPlayerName(playerName);
        playersOnMatch[playerIndex] = character;
        System.out.println(character.getName() + ", AKA " + character.getNickname() + ", has been assigned to " + character.getPlayerName());

    }

    public static String getPlayerName() {
//        String playerName;
        System.out.println("How about a name for me to call you (not your character's), so I can remind you about when it's your turn: ");
        return new Scanner(System.in).nextLine();
    }

    public static Criminal createCriminal() {
        Criminal createdCriminal;
        System.out.println("Character's name: ");
        String NAME = new Scanner(System.in).nextLine();
        System.out.println("Character's nickname: ");
        final String NICKNAME = new Scanner(System.in).nextLine();
        System.out.println("Character's birth year: ");
        final int BIRTH_YEAR = new Scanner(System.in).nextInt();
        System.out.println("Character's expertise");
        final String EXPERT_IN = new Scanner(System.in).nextLine();
        createdCriminal = new Criminal(NAME, NICKNAME, BIRTH_YEAR, EXPERT_IN);
        return createdCriminal;
    }

    public static Criminal createRandomCriminal() {
        Criminal randomCriminal;
        while (true) {
            short criminalsIndex = Utils.rangeFromZero((short) (Assets.defaultCriminals.length ));
            if (Assets.defaultCriminals[criminalsIndex] != null) {
                randomCriminal = new Criminal(Assets.defaultCriminals[criminalsIndex]);
                Assets.defaultCriminals[criminalsIndex] = null;
                break;
            }
        }
        return randomCriminal;
    }

    public static String twoDecimals(double number) {
        DecimalFormat formattedValue = new DecimalFormat("#.##");
        return formattedValue.format(number);
    }


    public static Detective pickRandomDetective() {
        short detectivesRandomIndex = Utils.rangeFromZero((short) (Assets.DETECTIVES.length - 1));
        return new Detective(Assets.DETECTIVES[detectivesRandomIndex]);
    }

    public static void clearAll(){
        for(Criminal player : Assets.getPlayerPool()){
            player.setEscaped(false);
            player.setArrested(false);
            Arrays.fill(player.getItems(), null);
            }
        Assets.detective = Utils.pickRandomDetective();
        Assets.bank.setSecurityPercentage(22);
        Assets.mansion.setSecurityPercentage(16);
        Assets.postOffice.setSecurityPercentage(13);
        Assets.supermarket.setSecurityPercentage(8);
        }

    public static void getHaulForAllCriminals(){
        for(Criminal player : Assets.getPlayerPool()){
            System.out.println(player.getPlayerName() + "'s haul: $" + player.getTotalValueForItems());
        }
    }
    public static void addItemsToCharacter(Criminal player, Item item) {
        Item[] detectiveItems = Assets.detective.getItems();
        Item[] playerItems = player.getItems();
        int playerMaxLootSize = player.getItems().length;
        int detectiveMaxLootSize = Assets.detective.getItems().length;

        for (int playerItemIndex = 0; playerItemIndex < playerMaxLootSize; playerItemIndex++) {
            if (!player.isArrested()) {
                if (playerItems[playerItemIndex] == null) {
                    playerItems[playerItemIndex] = item;
                    break;
                }
            } else {
                for (int detectiveItemIndex = 0; detectiveItemIndex < detectiveMaxLootSize; detectiveItemIndex++) {
                    if (detectiveItems[detectiveItemIndex] == null) {
                        detectiveItems[detectiveItemIndex] = playerItems[playerItemIndex];
                        break;
                    }
                }
            }
        }
    }
}
