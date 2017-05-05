package com.mitso.testapp.models.json_result;

import java.util.List;

public class Result {

    private String wrapperType;
    private String kind;
    private Integer artistId;
    private Integer collectionId;
    private Integer trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String feedUrl;
    private String trackViewUrl;
    private String artworkUrl30;
    private String artworkUrl60;
    private String artworkUrl100;
    private Double collectionPrice;
    private Double trackPrice;
    private Double trackRentalPrice;
    private Double collectionHdPrice;
    private Double trackHdPrice;
    private Double trackHdRentalPrice;
    private String releaseDate;
    private String collectionExplicitness;
    private String trackExplicitness;
    private Integer trackCount;
    private String country;
    private String currency;
    private String primaryGenreName;
    private String contentAdvisoryRating;
    private String artworkUrl600;
    private List<String> genreIds;
    private List<String> genres;
    private Integer amgArtistId;
    private String copyright;
    private Integer collectionArtistId;
    private String collectionArtistViewUrl;
    private Integer discCount;
    private Integer discNumber;
    private Integer trackNumber;
    private Integer trackTimeMillis;
    private String shortDescription;
    private String longDescription;
    private Boolean hasITunesExtras;

    @Override
    public String toString() {
        return "Result{" + "\n" +
                "wrapperType=" + wrapperType + "\n" +
                "kind=" + kind + "\n" +
                "artistId=" + artistId + "\n" +
                "collectionId=" + collectionId + "\n" +
                "trackId=" + trackId + "\n" +
                "artistName=" + artistName + "\n" +
                "collectionName=" + collectionName + "\n" +
                "trackName=" + trackName + "\n" +
                "collectionCensoredName='" + collectionCensoredName + "\n" +
                "trackCensoredName=" + trackCensoredName + "\n" +
                "artistViewUrl=" + artistViewUrl + "\n" +
                "collectionViewUrl=" + collectionViewUrl + "\n" +
                "feedUrl=" + feedUrl + "\n" +
                "trackViewUrl=" + trackViewUrl + "\n" +
                "artworkUrl30=" + artworkUrl30 + "\n" +
                "artworkUrl60=" + artworkUrl60 + "\n" +
                "artworkUrl100=" + artworkUrl100 + "\n" +
                "collectionPrice=" + collectionPrice + "\n" +
                "trackPrice=" + trackPrice + "\n" +
                "trackRentalPrice=" + trackRentalPrice + "\n" +
                "collectionHdPrice=" + collectionHdPrice + "\n" +
                "trackHdPrice=" + trackHdPrice + "\n" +
                "trackHdRentalPrice=" + trackHdRentalPrice + "\n" +
                "releaseDate=" + releaseDate + "\n" +
                "collectionExplicitness=" + collectionExplicitness + "\n" +
                "trackExplicitness=" + trackExplicitness + "\n" +
                "trackCount=" + trackCount + "\n" +
                "country=" + country + "\n" +
                "currency=" + currency + "\n" +
                "primaryGenreName=" + primaryGenreName + "\n" +
                "contentAdvisoryRating=" + contentAdvisoryRating + "\n" +
                "artworkUrl600=" + artworkUrl600 + "\n" +
                "genreIds=" + genreIds + "\n" +
                "genres=" + genres + "\n" +
                "amgArtistId=" + amgArtistId + "\n" +
                "copyright=" + copyright + "\n" +
                "collectionArtistId=" + collectionArtistId + "\n" +
                "collectionArtistViewUrl='" + collectionArtistViewUrl + "\n" +
                "discCount=" + discCount + "\n" +
                "discNumber=" + discNumber + "\n" +
                "trackNumber=" + trackNumber + "\n" +
                "trackTimeMillis=" + trackTimeMillis + "\n" +
                "shortDescription='" + shortDescription + "\n" +
                "longDescription='" + longDescription + "\n" +
                "hasITunesExtras=" + hasITunesExtras + "\n" +
                '}';
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public Double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public void setTrackRentalPrice(Double trackRentalPrice) {
        this.trackRentalPrice = trackRentalPrice;
    }

    public Double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    public void setCollectionHdPrice(Double collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }

    public Double getTrackHdPrice() {
        return trackHdPrice;
    }

    public void setTrackHdPrice(Double trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }

    public Double getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    public void setTrackHdRentalPrice(Double trackHdRentalPrice) {
        this.trackHdRentalPrice = trackHdRentalPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public String getArtworkUrl600() {
        return artworkUrl600;
    }

    public void setArtworkUrl600(String artworkUrl600) {
        this.artworkUrl600 = artworkUrl600;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(Integer amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getCollectionArtistId() {
        return collectionArtistId;
    }

    public void setCollectionArtistId(Integer collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Boolean getHasITunesExtras() {
        return hasITunesExtras;
    }

    public void setHasITunesExtras(Boolean hasITunesExtras) {
        this.hasITunesExtras = hasITunesExtras;
    }
}