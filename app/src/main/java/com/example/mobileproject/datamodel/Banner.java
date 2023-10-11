package com.example.mobileproject.datamodel;

public class Banner {
    private int id;
    private int resourceId;
    private String name;

    public Banner(int id, int resourceId, String name) {
        this.id = id;
        this.resourceId = resourceId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
