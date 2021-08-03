package com.intermediate.thegreatrobbery;

public class Detective extends Person{
    private double successPercentage = 20.0;
    private double increasedSuccessPercentagePerRound = 4.0;

    public Detective(String NAME, String NICKNAME, int BIRTH_YEAR, String EXPERT_IN) {
        super(NAME, NICKNAME, BIRTH_YEAR, EXPERT_IN);
        Item[] items = new Item[30];
    }

    public Detective(Detective detectiveObject) {
        super(detectiveObject.getName(), detectiveObject.getNickname(),
                detectiveObject.getBirthYear(), detectiveObject.getExpertIn());
        this.successPercentage = detectiveObject.successPercentage;
        this.increasedSuccessPercentagePerRound = detectiveObject.increasedSuccessPercentagePerRound;

    }

    public void setIncreasedSuccessPercentagePerRound(double increasedSuccessPercentagePerRound) {
        this.increasedSuccessPercentagePerRound = increasedSuccessPercentagePerRound;
    }

    public double getSuccessPercentage() {
        return successPercentage;
    }

//    public void setSuccessPercentage(double successPercentage) {
//        this.successPercentage = successPercentage;
//    }

    public double getIncreasedSuccessPercentagePerRound() {
        return increasedSuccessPercentagePerRound;
    }

    public String randomLocationToCheck(){
        short randomLocationIndex = Utils.rangeFromZero((short) (Assets.getCityBuildings().length - 1));
        return Assets.getCityBuildings()[randomLocationIndex].getName();

    }

    // Not implemented yet
    public void expertBio() {
        switch (this.getExpertIn()) {
            case "Police procedure" -> {
                System.out.println(this.getName() + " has been at the job for long and acquired a bit of everything along the way, becoming the well known and beloved jack of all trades");
                System.out.println(this.getNickname() + " doesn't super exceed in any particular skill, but moderately does a bit of everything: ");
                System.out.println("Every three rounds, picks from three locations instead of the usual four.");
                System.out.println("Increases the security check, if at the same location as you, by 25% instead of the usual 20%.");
                System.out.println("Every time a place is robbed, their security is increased by 3.5%, instead of the usual 2%");
            }
            case "Deduction" -> {
                System.out.println(this.getName() + " discards the less obvious location when looking for criminals.");
                System.out.println(this.getNickname() + " picks from three locations instead of the usual four. In other words, you have ~25% more chances to get acquainted.");
            }
            case "Hand-to-hand combat" -> {
                System.out.println(this.getName() + " is a master at non-lethal combat. That doesn't by all means say he won't mess you up in the process.");
                System.out.println(this.getNickname() + " increases the security check, if at the same location as you, by 35% instead of the usual 20%.");
            }
            case "Security" -> {
                System.out.println(this.getName() + " improves the security of local places hit by criminals. This is looking worse by the minute");
                System.out.println(this.getName() + " provides assistance to the places robbed, increasing the security level per round by 6% instead of the usual 2%.");
            }
        }
    }
    //
    public void makeArrest(Criminal player, Building building){
        if (building.isDetectivePresent()) {
            System.out.println("Detective " + this.getName() + " was staking the place and caught " + player.getName() + " on the act! The detective will be securing the scene at this location until the end of the round.");
        } else {
            System.out.println(player.getName() + " was caught by security. It looks like " + player.getNickname() + " will be taking some unwanted vacation time");
        }
        player.setArrested(true);
        Utils.addItemsToCharacter(player, null);

//               }
//            }
//        }
    }

    public void printBioData(){
        System.out.println("Detective: ");
        super.printBioData();
        this.expertBio();
    }

    // CHARACTER PERKS
//    public void addPerks() {
//        if(detective.getExpertIn().equals("Police Procedure")) {
//
//        }else if(detective.getExpertIn().equals("Deduction")) {
//            detective.setIncreasedSuccessPercentagePerRound(detective.getIncreasedSuccessPercentagePerRound() + 2.5);
//
//        }else if(detective.getExpertIn().equals("Hand-to-hand combat")) {
//
//        }else if(detective.getExpertIn().equals("Security")) {
//
//        }else {
//
//        }
//    }
}