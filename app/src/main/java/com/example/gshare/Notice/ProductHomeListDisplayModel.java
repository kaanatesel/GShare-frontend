package com.example.gshare.Notice;

public class ProductHomeListDisplayModel {
    private int id;
    private int g;
    private String description;
    private String name;

    public int getId() {
        return id;
    }

    public ProductHomeListDisplayModel(int id,int g,String name,String description) {
        this.description = description;
        this.name = name;
        this.id = id;
        this.g = g;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setG(int g) {
        this.g = g;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getG() {
        return g;
    }

    public String getDescription() {
        return description;
    }
}
