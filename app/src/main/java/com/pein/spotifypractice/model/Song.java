package com.pein.spotifypractice.model;

import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "song_table")
public class Song  {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "path")
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "artist")
    private String artist;

    @ColumnInfo(name = "duration")
    private String duration;

    @ColumnInfo(name = "album")
    private String album;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "favorite")
    private String favorite;

    public Song() {
    }

    @NonNull
    public String getPath() {
        return path;
    }

    public void setPath(@NonNull String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
