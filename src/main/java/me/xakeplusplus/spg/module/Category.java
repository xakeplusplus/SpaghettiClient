package me.xakeplusplus.spg.module;

public enum Category {
    COMBAT("COMBAT"), MOVEMENT("MOVEMENT"), MISC("MISC"), EXPLOITS("EXPLOITS"),
    CHAT("CHAT"), RENDER("RENDER");

    public String name;
    public int moduleIndex;

    Category(String name) {
        this.name = name;
    }
}
