package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

public class ChinhSach {
    @SerializedName("noiDungChinhSach")
    private String noiDungChinhSach;

    public ChinhSach(String noiDungChinhSach) {
        this.noiDungChinhSach = noiDungChinhSach;
    }
    public ChinhSach() {

    }
    public String getNoiDungChinhSach() {
        return noiDungChinhSach;
    }

    public void setNoiDungChinhSach(String noiDungChinhSach) {
        this.noiDungChinhSach = noiDungChinhSach;
    }
}
