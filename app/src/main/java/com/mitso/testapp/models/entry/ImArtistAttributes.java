package com.mitso.testapp.models.entry;

public class ImArtistAttributes {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return
                "ImArtistAttributes{" +
                "href='" + href + '\'' +
                '}';
    }
}