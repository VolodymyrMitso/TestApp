package com.mitso.testapp.models.entry;

public class EntrySummary {

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
                "EntrySummary{" +
                "label='" + label + '\'' +
                '}';
    }
}