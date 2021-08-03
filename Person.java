package com.intermediate.thegreatrobbery;

public abstract class Person {
    final private String NAME;
    final private String NICKNAME;
    final private int BIRTH_YEAR;
    final private String EXPERT_IN;
    private Item[] items;

    public Person(final String NAME, final String NICKNAME, final int BIRTH_YEAR, final String EXPERT_IN) {
        this.NAME = NAME;
        this.NICKNAME = NICKNAME;
        this.BIRTH_YEAR = BIRTH_YEAR;
        this.EXPERT_IN = EXPERT_IN;
        this.items = new Item[10];
    }

    public String getName() {
        return NAME;
    }

    public int getBirthYear() { return BIRTH_YEAR; }

    public String getNickname() {
        return NICKNAME;
    }

    public String getExpertIn() {
        return EXPERT_IN;
    }

    public Item[] getItems() {
        return items;
    }

    public short getTotalValueForItems() {
        short totalValue = 0;
        for(Item item : this.items){
            if(item != null){
                totalValue += item.getVALUE();
            }
        }
        return totalValue;
    }

    public void printBioData(){
        System.out.println("Name: " + NAME);
        System.out.println("Nickname: " + NICKNAME);
        System.out.println("Year of Birth: " + BIRTH_YEAR);
        System.out.println("Expert in: " + EXPERT_IN);
        System.out.println("--------------------------------------");
    }
}