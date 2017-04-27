package com.mitso.testapp.models.feed;

public class Author {

    private Name name;
    private Uri uri;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return
                "Author{" +
                "name=" + name +
                ", uri=" + uri +
                '}';
    }
}