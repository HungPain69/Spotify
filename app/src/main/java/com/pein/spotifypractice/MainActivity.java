package com.pein.spotifypractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.pein.spotifypractice.fragment.AlbumListFragment;
import com.pein.spotifypractice.fragment.ArtistFragment;
import com.pein.spotifypractice.fragment.PlaylistFragment;
import com.pein.spotifypractice.fragment.SongListFragment;
import com.pein.spotifypractice.fragment.SongPlayerFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        handleView();
    }

    private void handleView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tablayout);
        mViewPager.setOffscreenPageLimit(4);
        setupViewPager(mViewPager);
        mTablayout.setupWithViewPager(mViewPager);

        Fragment songPlayerFragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if(songPlayerFragment == null){
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, new SongPlayerFragment());
        }else {

        }
    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager(),0);
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
}