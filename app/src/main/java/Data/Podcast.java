package Data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class Podcast {

    public Podcast(String name, String title, String desc, Date date) {
        this.name = name;
        this.artistName = title;
        this.desc = desc;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLargeArtwork() {
        return largeArtwork;
    }

    public void setLargeArtwork(String largeArtwork) {
        this.largeArtwork = largeArtwork;
    }

    public String getSmallArtwork() {
        return smallArtwork;
    }

    public void setSmallArtwork(String smallArtwork) {
        this.smallArtwork = smallArtwork;
    }

    @SerializedName("collectionName")
    private String name;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("primaryGenreName")
    private String desc;
    @SerializedName("releaseDate")
    private Date date;
    @SerializedName("artworkUrl60")
    private String smallArtwork;
    @SerializedName("artworkUrl600")
    private String largeArtwork;
}
