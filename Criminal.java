package com.intermediate.thegreatrobbery;

public class Criminal extends Person {
    private Item[] ITEMS;
    private boolean Arrested;
    private boolean Escaped;
    private String playerName;


    public Criminal(final String NAME, final String NICKNAME, final int BIRTH_YEAR, final String EXPERT_IN) {
        super(NAME, NICKNAME, BIRTH_YEAR, EXPERT_IN);
        this.ITEMS = new Item[20];
        this.Arrested = false;
        this.Escaped = false;
        this.playerName = getPlayerName();
    }

    public Criminal(Criminal criminal) {
        super(criminal.getName(), criminal.getNickname(), criminal.getBirthYear(), criminal.getExpertIn());
        this.ITEMS = new Item[20];
        this.Arrested = false;
        this.Escaped = false;
        this.playerName = getPlayerName();

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSuccessPercentage() {
        return Utils.rangeFromZero((short) 100);
    }

    public boolean isArrested() {
        return Arrested;
    }

    public void setArrested(boolean arrested) {
        Arrested = arrested;
    }

    public boolean hasEscaped() {
        return Escaped;
    }

    public void setEscaped(boolean hasEscaped) {
        this.Escaped = hasEscaped;
    }

    public void runAway(){
        this.setEscaped(true);
        System.out.println(this.getName() + " decided it's time to bail. If there's anyone remaining, good luck!");
        System.out.println("Their total loot was $" + this.getTotalValueForItems());
        System.out.println("--------------------------------------------");
    }

    public void listHaulItems() {
        byte itemIndex = 1;
        String statusMessage = this.isArrested() ? "This were the items " + this.getName() + " had before being arrested: " :
                " List of " + this.getName() + "'s stolen items: ";
        System.out.println(statusMessage);
        for (Item item : this.getItems()) {
            if(item != null){
                System.out.println(itemIndex + ". " + item);
                itemIndex++;
            }else{ break; }

        }
        System.out.println(this.getName() + "'s haul is equal to: $" + this.getTotalValueForItems());
    }

    public void printBioData() {
        System.out.println("Criminal Person: ");
        super.printBioData();
    }

    public void theft(String playerPrompt) {
        int playerSuccessCheck = this.getSuccessPercentage();
        boolean wasSuccessful;
        String detectiveLocation = Assets.detectiveCurrentLocation = Assets.detective.randomLocationToCheck();
        for (Building building : Assets.getCityBuildings()) {
            if (building.getName().toLowerCase().equals(playerPrompt)) {
                if (detectiveLocation.equals(building.getName())) {
                    wasSuccessful = playerSuccessCheck >= building.getSecurityPercentage() + Assets.detective.getSuccessPercentage();
                    building.setDetectivePresent(true);
                } else
                {
                    wasSuccessful = playerSuccessCheck >= building.getSecurityPercentage();
                }

                if (wasSuccessful) {
                    this.saveStolenItem(building, playerSuccessCheck);
                } else {
                    Assets.detective.makeArrest(this, building);
                    System.out.println("The people from the " + building.getName() + " will be on higher alert now. Their security has been increased by a small amount.");
                    building.setSecurityPercentage(building.getSecurityPercentage() + 2);
                }
            }
        }
    }

        public void saveStolenItem(Building building, int playerSuccessCheck) {
            short randomItemIndex = Utils.rangeFromZero((short) (building.getItems().length - 1));
            Item stolenItem = new Item(building.getItems()[randomItemIndex]);
            if(!stolenItem.getNAME().equals("nothing")){
                Utils.addItemsToCharacter(this, stolenItem);
            }
            if(building.isDetectivePresent()){
                System.out.println("Despite detective's " + Assets.detective.getName() + " presence at the scene, " + this.getName() + " still managed to escape the scene.");
                System.out.println(Assets.detective.getNickname() + " took a hit to their professional reliance, reducing their security check by 5%");
                Assets.detective.setIncreasedSuccessPercentagePerRound(Assets.detective.getIncreasedSuccessPercentagePerRound() - 3);
            }else {
                System.out.println("While the theft at the " + building.getName() + " was taking place, detective " + Assets.detective.getName() + " was checking the " + Assets.detectiveCurrentLocation);
                System.out.println(Assets.detective.getNickname() + " vowed to do better in the future. Their security check increases by 3%");
                Assets.detective.setIncreasedSuccessPercentagePerRound(Assets.detective.getIncreasedSuccessPercentagePerRound() + 3);
            }
            System.out.println("------------------------------------------------------------------------------");
            building.setDetectivePresent(false);
            if(stolenItem.getNAME().equals("nothing")){
                System.out.println(this.getName() + " decided that despite the chances, it would be too risk to hit the place. " +
                        this.getNickname() + " got away from the " + building.getName() + " with nothing. The success rate was: " + playerSuccessCheck +
                        "% against " + Utils.twoDecimals(building.getSecurityPercentage()) + "% from the " + building.getName());
            }
            System.out.println(this.getName() + " managed to get away from the " + building.getName() + " with " +
                    stolenItem.getNAME() + " worth $" + stolenItem.getValue() + ". " + this.getNickname() +
                    " success rate was: " + playerSuccessCheck +
                    "% against " + Utils.twoDecimals(building.getSecurityPercentage()) + "% from the " + building.getName());
            System.out.println("------------------------------------------------------------------------------");
    }
}