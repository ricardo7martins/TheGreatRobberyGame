package com.intermediate.thegreatrobbery;

public record City(String NAME, Building[] cityBuildings) {

    public Building[] getCityBuildings() {
        return cityBuildings;
    }

    public static void increaseSecurity() {
        double securityIncrease = Assets.detective.getExpertIn().equalsIgnoreCase("Security") ? 1.08 : 1.05;
        for (Building building : Assets.city.getCityBuildings()) {
            building.setSecurityPercentage(building.getSecurityPercentage() + securityIncrease);
        }
    }
}