package com.mitso.testapp.models.entry;

import com.google.gson.annotations.SerializedName;

public class EntryIdAttributes {

    @SerializedName("im:id")
    private String imId;

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    @Override
    public boolean equals(Object _object) {

        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        final EntryIdAttributes that = (EntryIdAttributes) _object;

        return imId.equals(that.imId);
    }

    @Override
    public int hashCode() {

        return imId.hashCode();
    }

    @Override
    public String toString() {
        return
                "EntryIdAttributes{" +
                "imId='" + imId + '\'' +
                '}';
    }
}
