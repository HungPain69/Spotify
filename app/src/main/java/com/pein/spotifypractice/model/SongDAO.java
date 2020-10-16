package com.pein.spotifypractice.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Song... songs);

    @Query("Select * from song_table")
    LiveData<List<Song>> getAllSong();

    @Query("Delete from song_table")
    void deleteAll();

}
