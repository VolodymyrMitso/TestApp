package com.mitso.testapp.models.json_entry_list.feed;

import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("attributes")
    private LinkAttributes attributes;

    public LinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "Link{" +
                "attributes=" + attributes +
                '}';
    }
}