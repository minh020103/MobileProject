package com.example.mobileprojectapp2.model;

public class ResultForgotPassword {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultForgotPassword(String message) {
        this.message = message;
    }
}
