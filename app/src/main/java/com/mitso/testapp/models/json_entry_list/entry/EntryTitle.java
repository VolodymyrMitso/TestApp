package com.mitso.testapp.models.json_entry_list.entry;

public class EntryTitle {

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
                "EntryTitle{" +
                "label='" + label + '\'' +
                '}';
    }
}
