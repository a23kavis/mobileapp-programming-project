package com.example.project;

@SuppressWarnings("WeakerAccess")
public class RecyclerViewItem {

    private String name;
    private String location;
    private int size;
    private String imageView;

    public RecyclerViewItem(String name, String location, int size, String imageView) {
        this.name = name;
        this.location = location;
        this.size = size;
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public String getImageView() {
        return imageView;
    }
}
