package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class ImContentType {

    @SerializedName("attributes")
    private ImContentTypeAttributes attributes;

    public ImContentTypeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImContentTypeAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "ImContentType{" +
                "attributes=" + attributes +
                '}';
    }
}
