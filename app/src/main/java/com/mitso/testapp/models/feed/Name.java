package com.mitso.testapp.models.feed;

public class Name {

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
                "Name{" +
                "label='" + label + '\'' +
                '}';
    }
}