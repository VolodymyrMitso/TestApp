package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class EntryLink {

    @SerializedName("attributes")
    private EntryLinkAttributes attributes;
    @SerializedName("im:duration")
    private ImDuration imDuration;

    public EntryLinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EntryLinkAttributes attributes) {
        this.attributes = attributes;
    }

    public ImDuration getImDuration() {
        return imDuration;
    }

    public void setImDuration(ImDuration imDuration) {
        this.imDuration = imDuration;
    }

    @Override
    public String toString() {
        return
                "EntryLink{" +
                "attributes=" + attributes +
                ", imDuration=" + imDuration +
                '}';
    }
}
