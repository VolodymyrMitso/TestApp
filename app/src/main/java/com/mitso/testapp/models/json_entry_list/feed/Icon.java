package com.mitso.testapp.models.json_entry_list.feed;

public class Icon {

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
                "Icon{" +
                "label='" + label + '\'' +
                '}';
    }
}