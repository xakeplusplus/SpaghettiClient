package me.xakeplusplus.spg.module;

public enum Category {
    COMBAT("COMBAT"), MOVEMENT("MOVEMENT"), MISC("MISC"), EXPLOITS("EXPLOITS"),
    CHAT("CHAT"), PLAYER("PLAYER"), RENDER("RENDER");

    public String name;
    public int moduleIndex;

    Category(String name) {
        this.name = name;
    }
}
