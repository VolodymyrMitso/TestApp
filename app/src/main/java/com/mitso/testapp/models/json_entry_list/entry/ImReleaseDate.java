package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class ImReleaseDate {

    private String label;
    @SerializedName("attributes")
    private ImReleaseDateAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImReleaseDateAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImReleaseDateAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "ImReleaseDate{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}