package ru.mirea.blinnikovkm.recyclerviewapp;

public class HistoricalEvent {
    private String name;
    private String desc;
    private String imgUrl;
    public HistoricalEvent(String name, String desc, String imgUrl) {
        this.name= name;
        this.desc= desc;
        this.imgUrl= imgUrl;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    @Override
    public String toString() {
        return this.name + " - " + this.desc;
    }
}
