package com.pein.spotifypractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pein.spotifypractice.R;
import com.pein.spotifypractice.databinding.ItemBinding;
import com.pein.spotifypractice.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<Song> mListSong;
    private Context mContext;

    public SongAdapter(List<Song> mListSong, Context mContext) {
        this.mListSong = mListSong;
        this.mContext = mContext;
    }

    public SongAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        Song song = mListSong.get(position);
        holder.nameSong.setText(song.getName());
        loadImage(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListSong == null ? 0 : mListSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        ImageView imageSong;
        TextView nameSong;

        public ViewHolder(@NonNull ItemBinding itemView) {
            super(itemView.getRoot());
            container = itemView.container;
            imageSong = itemView.imgSong;
            nameSong = itemView.songName;
        }
    }

    public void setData(List<Song> songList) {
        mListSong = songList;
        notifyDataSetChanged();
    }

    private void loadImage(SongAdapter.ViewHolder holder, int position) {
        Song song = mListSong.get(position);
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(song.getPath());
            byte[] artBytes = mmr.getEmbeddedPicture();
            if (artBytes != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
                holder.imageSong.setImageBitmap(bm);
            } else {
                holder.imageSong.setImageResource(R.drawable.ic_music);
            }
        } catch (Exception e) {
        }
    }
}
