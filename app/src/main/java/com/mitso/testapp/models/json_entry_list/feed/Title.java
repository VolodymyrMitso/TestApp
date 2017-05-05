package com.mitso.testapp.models.json_entry_list.feed;

public class Title {

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
                "Title{" +
                "label='" + label + '\'' +
                '}';
    }
}