package com.example.project;

@SuppressWarnings("WeakerAccess")
public class RecyclerViewItem {

    private String title;
    private String location;
    private int size;
    private String auxdata;

    public RecyclerViewItem(String title, String location, int size, String auxdata) {
        this.title = title;
        this.location = location;
        this.size = size;
        this.auxdata = auxdata;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public String getAuxdata() {
        return auxdata;
    }
}
