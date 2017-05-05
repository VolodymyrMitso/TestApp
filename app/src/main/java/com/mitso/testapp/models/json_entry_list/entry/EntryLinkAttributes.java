package com.mitso.testapp.models.json_entry_list.entry;

import com.google.gson.annotations.SerializedName;

public class EntryLinkAttributes {

    private String rel;
    private String type;
    private String href;
    private String title;
    @SerializedName("im:assetType")
    private String imAssetType;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImAssetType() {
        return imAssetType;
    }

    public void setImAssetType(String imAssetType) {
        this.imAssetType = imAssetType;
    }

    @Override
    public String toString() {
        return
                "EntryLinkAttributes{" +
                "rel='" + rel + '\'' +
                ", type='" + type + '\'' +
                ", href='" + href + '\'' +
                ", title='" + title + '\'' +
                ", imAssetType='" + imAssetType + '\'' +
                '}';
    }
}