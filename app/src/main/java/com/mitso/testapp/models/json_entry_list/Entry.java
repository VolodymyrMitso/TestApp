package com.mitso.testapp.models.json_entry_list;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.mitso.testapp.models.json_entry_list.entry.EntryCategory;
import com.mitso.testapp.models.json_entry_list.entry.EntryId;
import com.mitso.testapp.models.json_entry_list.entry.EntryLink;
import com.mitso.testapp.models.json_entry_list.entry.EntryRights;
import com.mitso.testapp.models.json_entry_list.entry.EntrySummary;
import com.mitso.testapp.models.json_entry_list.entry.EntryTitle;
import com.mitso.testapp.models.json_entry_list.entry.ImArtist;
import com.mitso.testapp.models.json_entry_list.entry.ImContentType;
import com.mitso.testapp.models.json_entry_list.entry.ImImage;
import com.mitso.testapp.models.json_entry_list.entry.ImName;
import com.mitso.testapp.models.json_entry_list.entry.ImPrice;
import com.mitso.testapp.models.json_entry_list.entry.ImReleaseDate;
import com.mitso.testapp.models.recycler_view.BaseModel;
import com.mitso.testapp.models.type_adapter.LinkAdapterFactory;

import java.io.Serializable;
import java.util.List;

public class Entry extends BaseModel implements Serializable {

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

    @Override
    public int getType() {

        return TYPE_ENTRY;
    }

    @Override
    public boolean equals(Object _object) {

        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        final Entry entry = (Entry) _object;

        return id.equals(entry.id);
    }

    @Override
    public int hashCode() {

        return id.hashCode();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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
}
