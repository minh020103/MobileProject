package com.example.mobileproject.datamodel;

public class Banner {
    private int id;
    private String hinhBanner;

    public Banner(int id, String hinhBanner) {
        this.id = id;
        this.hinhBanner = hinhBanner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinhBanner() {
        return hinhBanner;
    }

    public void setHinhBanner(String hinhBanner) {
        this.hinhBanner = hinhBanner;
    }
}
