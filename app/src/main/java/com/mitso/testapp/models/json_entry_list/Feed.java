package com.mitso.testapp.models.json_entry_list;

import com.mitso.testapp.models.json_entry_list.feed.Author;
import com.mitso.testapp.models.json_entry_list.feed.Icon;
import com.mitso.testapp.models.json_entry_list.feed.Id;
import com.mitso.testapp.models.json_entry_list.feed.Link;
import com.mitso.testapp.models.json_entry_list.feed.Rights;
import com.mitso.testapp.models.json_entry_list.feed.Title;
import com.mitso.testapp.models.json_entry_list.feed.Updated;

import java.util.List;

public class Feed {

    private Author author;
    private List<Entry> entry;
    private Updated updated;
    private Rights rights;
    private Title title;
    private Icon icon;
    private List<Link> link;
    private Id id;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "Feed{" +
                "author=" + author +
                ", entry=" + entry +
                ", updated=" + updated +
                ", rights=" + rights +
                ", title=" + title +
                ", icon=" + icon +
                ", link=" + link +
                ", id=" + id +
                '}';
    }
}