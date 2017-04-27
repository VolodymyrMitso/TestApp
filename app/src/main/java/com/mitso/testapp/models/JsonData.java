package com.mitso.testapp.models;

public class JsonData {

    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return
                "JsonData{" +
                "feed=" + feed +
                '}';
    }
}