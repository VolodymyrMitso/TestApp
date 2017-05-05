package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class ImPrice {

    private String label;
    @SerializedName("attributes")
    private ImPriceAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImPriceAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImPriceAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "ImPrice{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}