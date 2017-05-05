package com.mitso.testapp.models.json_entry_list;

public class JsonEntryList {

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
                "JsonEntryList{" +
                "feed=" + feed +
                '}';
    }
}