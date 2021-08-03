package com.intermediate.thegreatrobbery;

public abstract class Assets {
    private static Criminal[] playerPool;

    // Default criminals:
    static Criminal robby = new Criminal("Robby", "The Mountain", 1992, "Skull crushing!!!");
    static Criminal bob = new Criminal("Bob", "The Head", 1986, "Heist planning");
    static Criminal jane = new Criminal("Jane", "The Wind", 1990, "Breaking and Entering");
    static Criminal marcy = new Criminal("Marcy", "The Whisper", 1988, "Stealth");
    public static Criminal[] defaultCriminals = {robby, bob, jane, marcy};


    public static Criminal[] getPlayerPool() {
        return playerPool;
    }

    public static void setPlayerPool(Criminal[] playerPool) {
        Assets.playerPool = playerPool;
    }


    // Detectives
    static Detective adamPalmer = new Detective("Adam Palmer", "The Coyote", 1970, "Police procedure");
    static Detective cassandraKnight = new Detective("Cassandra Knight", "The Owl", 1985, "Security");
    static Detective alexSanders = new Detective("Alex Sanders", "The Wolf", 1988, "Hand-to-hand combat");
    static Detective johannaWilliams = new Detective("Johanna Williams", "The Hound", 1980, "Deduction");
    static final Detective[] DETECTIVES = {adamPalmer, cassandraKnight, alexSanders, johannaWilliams};
    static Detective detective = Utils.pickRandomDetective();
    static String detectiveCurrentLocation;

    // Item creation
    static Item moneyBag = new Item("a money bag", 700, 1000);
    static Item nothing = new Item("nothing", 0, 0);
    static Item jewelry = new Item("a piece of jewelry", 150, 300);
    static Item pairOfFancyShoes = new Item("a pair of fancy shoes", 75, 150);
    static Item deliveryParcel = new Item("a delivery parcel", 60, 120);
    static Item pettyCash = new Item("petty cash", 30, 60);
    static Item cashRegister = new Item("cash register's money", 30, 60);
    static Item randomStuff = new Item("random stuff", 15, 30);

    // Loot creation
    static final Item [] BANK_ITEMS = {Assets.moneyBag, Assets.nothing, Assets.nothing, Assets.nothing};
    static final Item [] MANSION_ITEMS = {Assets.pairOfFancyShoes, Assets.jewelry};
    static final Item [] POST_OFFICE_ITEMS = {Assets.deliveryParcel, Assets.pettyCash};
    static final Item [] SUPERMARKET_ITEMS = {Assets.cashRegister, Assets.randomStuff};

    // City creation
    static Building bank = new Building("Bank", Assets.BANK_ITEMS, 22.0, 4.0);
    static Building mansion = new Building("Mansion", Assets.MANSION_ITEMS, 16.0, 3.0);
    static Building postOffice = new Building("Post Office", Assets.POST_OFFICE_ITEMS, 13.0, 2.0);
    static Building supermarket = new Building("Supermarket", Assets.SUPERMARKET_ITEMS, 8.0, 1.5);
    private static final Building[] cityBuildings = {Assets.bank, Assets.mansion, Assets.postOffice, Assets.supermarket};
    static City city = new City("Gotham City", cityBuildings);
    public static Building[] getCityBuildings() {
        return cityBuildings;
    }




}