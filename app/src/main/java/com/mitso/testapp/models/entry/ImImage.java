package com.mitso.testapp.models.entry;

import com.google.gson.annotations.SerializedName;

public class ImImage {

    private String label;
    @SerializedName("attributes")
    private ImImageAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImImageAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImImageAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "ImImage{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
