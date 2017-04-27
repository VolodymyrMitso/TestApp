package com.mitso.testapp.models.feed;

public class Uri {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return
                "Uri{" +
                "label='" + label + '\'' +
                '}';
    }
}