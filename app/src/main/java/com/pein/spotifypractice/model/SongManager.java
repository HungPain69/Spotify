package com.pein.spotifypractice.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
    public static List<Song> getFileFromStorage(Context context){

        List<Song> listSong = new ArrayList<>();
        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] proj = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
        };
        Cursor audioCursor = context.getContentResolver().query(uri, proj, null, null,null);
        if (audioCursor != null) {
            if (audioCursor.moveToFirst()) {
                do {
                    int title = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
                    int artist = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                    int duration = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
                    int path = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                    int album = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
                    int albumid = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);
                    int song_id = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
                    Song object = new Song();
                    object.setPath(audioCursor.getString(path));
                    object.setName(audioCursor.getString(title));
                    object.setDuration((audioCursor.getString(duration)));
                    object.setArtist(audioCursor.getString(artist));
                    object.setAlbum(audioCursor.getString(album));
//                    info.setId(audioCursor.getLong(song_id));
//                    info.setAlbum_art((ContentUris.withAppendedId(albumArtUri, audioCursor.getLong(audioalbumid))).toString());
                    listSong.add(object);
                } while (audioCursor.moveToNext());
            }
        }
        audioCursor.close();

        return listSong;
    };
}
