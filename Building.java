package com.intermediate.thegreatrobbery;

import java.text.DecimalFormat;

public class Building{
    private final String NAME;
    private final Item[] ITEMS;
    private double securityPercentage;
    private double securityIncreasePerRound;
    private boolean DetectivePresent;

    public String getName() {
        return NAME;
    }

    public Item[] getItems() {
        return ITEMS;
    }



    // Constructor

    public void setSecurityIncreasePerRound(double securityIncreasePerRound) {
        this.securityIncreasePerRound = securityIncreasePerRound;
    }

    public double getSecurityIncreasePerRound() {
        return securityIncreasePerRound;
    }

    public Building(final String NAME, final Item[] ITEMS, double securityPercentage, double roundSecurityIncrease) {
        this.NAME = NAME;
        this.ITEMS = ITEMS;
        this.securityPercentage = securityPercentage;
        this.securityIncreasePerRound = roundSecurityIncrease;
    }
    public void setSecurityPercentage(double securityPercentage) {
        this.securityPercentage = securityPercentage;
    }

    public void setDetectivePresent(boolean detectivePresent) {
        DetectivePresent = detectivePresent;
    }

    public double getSecurityPercentage() {
        return securityPercentage;
    }
    public boolean isDetectivePresent() {
        return DetectivePresent;
    }



}