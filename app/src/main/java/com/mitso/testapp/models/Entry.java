package com.mitso.testapp.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.mitso.testapp.models.adapter.LinkAdapterFactory;
import com.mitso.testapp.models.entry.EntryCategory;
import com.mitso.testapp.models.entry.EntryId;
import com.mitso.testapp.models.entry.EntryLink;
import com.mitso.testapp.models.entry.EntryRights;
import com.mitso.testapp.models.entry.EntrySummary;
import com.mitso.testapp.models.entry.EntryTitle;
import com.mitso.testapp.models.entry.ImArtist;
import com.mitso.testapp.models.entry.ImContentType;
import com.mitso.testapp.models.entry.ImImage;
import com.mitso.testapp.models.entry.ImName;
import com.mitso.testapp.models.entry.ImPrice;
import com.mitso.testapp.models.entry.ImReleaseDate;

import java.util.List;

public class Entry {

    @SerializedName("im:name")
    private ImName imName;
    @SerializedName("im:price")
    private ImPrice imPrice;
    @SerializedName("im:image")
    private List<ImImage> imImage;
    @SerializedName("im:artist")
    private ImArtist imArtist;
    @SerializedName("im:contentType")
    private ImContentType imContentType;
    @SerializedName("im:releaseDate")
    private ImReleaseDate imReleaseDate;

    @JsonAdapter(LinkAdapterFactory.class)
    @SerializedName("link")
    private List<EntryLink> link;
    @SerializedName("rights")
    private EntryRights rights;
    @SerializedName("title")
    private EntryTitle title;
    @SerializedName("id")
    private EntryId id;
    @SerializedName("category")
    private EntryCategory category;
    @SerializedName("summary")
    private EntrySummary summary;

    public ImName getImName() {
        return imName;
    }

    public void setImName(ImName imName) {
        this.imName = imName;
    }

    public EntryRights getRights() {
        return rights;
    }

    public void setRights(EntryRights rights) {
        this.rights = rights;
    }

    public ImPrice getImPrice() {
        return imPrice;
    }

    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    public List<ImImage> getImImage() {
        return imImage;
    }

    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    public EntryTitle getTitle() {
        return title;
    }

    public void setTitle(EntryTitle title) {
        this.title = title;
    }

    public List<EntryLink> getLink() {
        return link;
    }

    public void setLink(List<EntryLink> link) {
        this.link = link;
    }

    public EntryId getId() {
        return id;
    }

    public void setId(EntryId id) {
        this.id = id;
    }

    public ImContentType getImContentType() {
        return imContentType;
    }

    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

    public EntryCategory getCategory() {
        return category;
    }

    public void setCategory(EntryCategory category) {
        this.category = category;
    }

    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }

    public EntrySummary getSummary() {
        return summary;
    }

    public void setSummary(EntrySummary summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return
                "Entry{" + "\n" +
                "imName=" + imName + "\n" +
                "rights=" + rights + "\n" +
                "imPrice=" + imPrice + "\n" +
                "imImage=" + imImage + "\n" +
                "imArtist=" + imArtist + "\n" +
                "title=" + title + "\n" +
                "link=" + link + "\n" +
                "id=" + id + "\n" +
                "imContentType=" + imContentType + "\n" +
                "category=" + category + "\n" +
                "imReleaseDate=" + imReleaseDate + "\n" +
                "summary=" + summary + "\n" +
                '}';
    }
}