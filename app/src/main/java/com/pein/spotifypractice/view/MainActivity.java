package com.pein.spotifypractice.view;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pein.spotifypractice.R;
import com.pein.spotifypractice.databinding.ActivityMainBinding;
import com.pein.spotifypractice.fragment.AlbumListFragment;
import com.pein.spotifypractice.fragment.ArtistFragment;
import com.pein.spotifypractice.fragment.PlaylistFragment;
import com.pein.spotifypractice.fragment.SongListFragment;
import com.pein.spotifypractice.fragment.SongPlayerFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private ActivityMainBinding mBinding;
    public String hasPermission = "false";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        handleView();
        requestPermission();

        sharedPreferences = getSharedPreferences("Spotify_reference", Context.MODE_PRIVATE);
        mEditor = sharedPreferences.edit();
        mEditor.putBoolean("hasPermission", true);
    }

    private void handleView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = mBinding.viewpager;
        mTablayout = mBinding.tablayout;
        mBinding.viewpager.setOffscreenPageLimit(4);
        setupViewPager(mViewPager);
        mTablayout.setupWithViewPager(mViewPager);

        Fragment songPlayerFragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (songPlayerFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, new SongPlayerFragment());
        } else {

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager(), 0);
        SongListFragment songListFragment = new SongListFragment();
        AlbumListFragment albumListFragment = new AlbumListFragment();
        ArtistFragment artistFragment = new ArtistFragment();
        PlaylistFragment playlistFragment = new PlaylistFragment();

        adapter.addFragment(songListFragment, "Song");
        adapter.addFragment(albumListFragment, "Album");
        adapter.addFragment(artistFragment, "Artist");
        adapter.addFragment(playlistFragment, "Playlist");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                mEditor.putBoolean(hasPermission, false).commit();
                break;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this
                , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

}