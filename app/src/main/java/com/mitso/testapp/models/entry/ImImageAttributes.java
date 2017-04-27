package com.mitso.testapp.models.entry;

public class ImImageAttributes {

    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return
                "ImImageAttributes{" +
                "height='" + height + '\'' +
                '}';
    }
}