package com.example.bai4.model;

public class ItemModel {
    private String name;
    private int imageResource;

    // Constructor
    public ItemModel(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    // Getter method cho name
    public String getName() {
        return name;
    }

    // Getter method cho imageResource
    public int getImageResource() {
        return imageResource;
    }

    // Setter method nếu cần thay đổi dữ liệu
    public void setName(String name) {
        this.name = name;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
