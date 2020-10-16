package com.pein.spotifypractice.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pein.spotifypractice.model.Song;

import java.util.List;

public class SongViewModel extends AndroidViewModel {

    private SongRepository mSongRepository;
    private LiveData<List<Song>> mAllSong;


    public SongViewModel(@NonNull Application application) {
        super(application);
        mSongRepository = new SongRepository(application);
        mAllSong = mSongRepository.getmAllSong();
    }

    public LiveData<List<Song>> getmAllSong() {
        return mAllSong;
    }

    public void insert(Song song) {
        mSongRepository.insert(song);
    }
}
