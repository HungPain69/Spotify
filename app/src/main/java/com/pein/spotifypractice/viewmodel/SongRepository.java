package com.pein.spotifypractice.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pein.spotifypractice.model.Song;
import com.pein.spotifypractice.model.SongDAO;
import com.pein.spotifypractice.model.SongRoomDatabase;

import java.util.List;

public class SongRepository {
    private SongDAO mSongDao;
    private LiveData<List<Song>> mAllSong;

    SongRepository(Application application) {
        SongRoomDatabase db = SongRoomDatabase.getINSTANCE(application);
        mSongDao = db.songDAO();
        mAllSong = mSongDao.getAllSong();
    }

    LiveData<List<Song>> getmAllSong() {
        return mAllSong;
    }

    public void insert(Song song) {
        SongRoomDatabase.databaseWriterExecutor.execute(() -> {
            mSongDao.insert(song);
        });
    }
}
