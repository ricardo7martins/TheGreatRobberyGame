package com.intermediate.thegreatrobbery;

public class Item {
    private final String NAME;
    private final int VALUEMIN;
    private final int VALUEMAX;
    private int VALUE;

    public Item(final String NAME, final int VALUEMIN, final int VALUEMAX) {
        this.NAME = NAME;
        this.VALUEMIN = VALUEMIN;
        this.VALUEMAX = VALUEMAX;
        this.VALUE = Utils.itemRangeValue(VALUEMIN, VALUEMAX);
    }

    @Override
    public String toString() {
        return NAME + " - $" + VALUE;
    }

    public Item(Item item) {
        this.NAME = item.NAME;
        this.VALUE = item.VALUE;
        this.VALUEMIN = item.VALUEMIN;
        this.VALUEMAX = item.VALUEMAX;
    }

    public String getNAME() {
        return NAME;
    }

    public double getVALUE() {
        return VALUE;
    }

    public int getVALUEMIN() {
        return VALUEMIN;
    }

    public int getVALUEMAX() {
        return VALUEMAX;
    }

    public void setValue() {
        this.VALUE = Utils.itemRangeValue(this.getVALUEMIN(), this.getVALUEMAX());
    }

    public int getValue() {
        return VALUE;
    }
}