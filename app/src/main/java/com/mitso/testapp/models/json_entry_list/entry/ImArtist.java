package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class ImArtist {

    private String label;
    @SerializedName("attributes")
    private ImArtistAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImArtistAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImArtistAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "ImArtist{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
