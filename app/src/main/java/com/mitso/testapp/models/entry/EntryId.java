package com.mitso.testapp.models.entry;

import com.google.gson.annotations.SerializedName;

public class EntryId {

    private String label;
    @SerializedName("attributes")
    private EntryIdAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EntryIdAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EntryIdAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "EntryId{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}