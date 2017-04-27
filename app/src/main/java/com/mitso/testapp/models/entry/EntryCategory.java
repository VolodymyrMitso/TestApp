package com.mitso.testapp.models.entry;

public class EntryCategory {

    private EntryCategoryAttributes attributes;

    public EntryCategoryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EntryCategoryAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return
                "EntryCategory{" +
                "attributes=" + attributes +
                '}';
    }
}
