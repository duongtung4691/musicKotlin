package com.duongtung.musicexample.data

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.duongtung.musicexample.base.BaseAsynTaskLoader
import com.duongtung.musicexample.model.Music
import java.util.*


/**
 * Created by duong on 5/27/2017.
 */
class MusicLoader(context: Context) : BaseAsynTaskLoader<Collection<Music>>(context) {
    private val albumArtUri = Uri.parse("content://media/external/audio/albumart")

    override fun loadInBackground(): Collection<Music> {
        val projection = arrayOf(MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA)
        val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Audio.Media.IS_MUSIC + "=1", null,
                "LOWER(" + MediaStore.Audio.Media.ARTIST + ") ASC, " +
                        "LOWER(" + MediaStore.Audio.Media.ALBUM + ") ASC, " +
                        "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC"
        ) ?: return Collections.emptyList()
        val items = ArrayList<Music>()
        try {
            if (cursor.moveToFirst()) {
                val title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val album = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)
                val artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                val duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
                val albumId = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
                val data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                do {
                    val item = Music()
                            .title(cursor.getString(title))
                            .album(cursor.getString(album))
                            .artist(cursor.getString(artist))
                            .duration(cursor.getLong(duration))
                            .albumArtUri(ContentUris.withAppendedId(albumArtUri, cursor.getLong(albumId)))
                            .fileUri(Uri.parse(cursor.getString(data)))
                    if (item.duration() > 20000)
                        items.add(item)
                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
        }
        return items
    }
}