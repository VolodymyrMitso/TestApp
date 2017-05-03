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
    public boolean equals(Object _object) {

        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        final EntryId entryId = (EntryId) _object;

        if (!label.equals(entryId.label)) return false;
        return attributes.equals(entryId.attributes);
    }

    @Override
    public int hashCode() {

        int result = label.hashCode();
        result = 31 * result + attributes.hashCode();
        return result;
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