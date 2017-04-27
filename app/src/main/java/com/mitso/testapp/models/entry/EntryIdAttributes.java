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
    public String toString() {
        return
                "EntryIdAttributes{" +
                "imId='" + imId + '\'' +
                '}';
    }
}
