package com.pein.spotifypractice.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pein.spotifypractice.R;
import com.pein.spotifypractice.databinding.FragmentSongListBinding;
import com.pein.spotifypractice.model.Song;
import com.pein.spotifypractice.model.SongManager;
import com.pein.spotifypractice.view.SongAdapter;
import com.pein.spotifypractice.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongListFragment extends MusicFragment {

    private FragmentSongListBinding mBinding;
    private SongViewModel mViewModel;
    private List<Song> mListSongFromStorage;
    private Context mContext;
    private SongAdapter mSongAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Spotify_reference", Context.MODE_PRIVATE);
        boolean hasPermission = sharedPreferences.getBoolean("hasPermission", false);
        if (hasPermission) {
            mListSongFromStorage = new ArrayList<>();
            mListSongFromStorage = SongManager.getFileFromStorage(getActivity());
            for (Song song : mListSongFromStorage) {
                mViewModel.insert(song);
            }
        }

        mViewModel.getmAllSong().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mSongAdapter.setData(songs);
                mBinding.recylceView.setAdapter(mSongAdapter);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mSongAdapter = new SongAdapter(mContext);
        mViewModel = new ViewModelProvider(this).get(SongViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recylceView.setLayoutManager(new LinearLayoutManager(mContext));
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mBinding.recylceView.addItemDecoration(divider);
    }

}
