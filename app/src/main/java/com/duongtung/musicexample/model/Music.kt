package com.duongtung.musicexample.model

/**
 * Created by duong on 5/27/2017.
 */
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

/**
 * Music track model.
 */
class Music : Parcelable {
    private var title: String? = null
    private var album: String? = null
    private var artist: String? = null
    private var duration: Long = 0
    private var albumArtUri: Uri? = null
    private var fileUri: Uri? = null

    constructor() {}

    fun title(title: String): Music {
        this.title = title
        return this
    }

    fun album(album: String): Music {
        this.album = album
        return this
    }

    fun artist(artist: String): Music {
        this.artist = artist
        return this
    }

    fun duration(duration: Long): Music {
        this.duration = duration
        return this
    }

    fun albumArtUri(albumArtUri: Uri): Music {
        this.albumArtUri = albumArtUri
        return this
    }

    fun fileUri(fileUri: Uri): Music {
        this.fileUri = fileUri
        return this
    }

    fun title(): String? {
        return title
    }

    fun album(): String? {
        return album
    }

    fun artist(): String? {
        return artist
    }

    fun duration(): Long {
        return duration
    }

    fun albumArtUri(): Uri? {
        return albumArtUri
    }

    fun fileUri(): Uri? {
        return fileUri
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val item = o as Music?

        if (duration != item!!.duration) return false
        if (if (title != null) title != item.title else item.title != null) return false
        if (if (album != null) album != item.album else item.album != null) return false
        if (if (artist != null) artist != item.artist else item.artist != null) return false
        if (if (albumArtUri != null) albumArtUri != item.albumArtUri else item.albumArtUri != null)
            return false
        return if (fileUri != null) fileUri == item.fileUri else item.fileUri == null

    }

    override fun hashCode(): Int {
        var result = if (title != null) title!!.hashCode() else 0
        result = 31 * result + if (album != null) album!!.hashCode() else 0
        result = 31 * result + if (artist != null) artist!!.hashCode() else 0
        result = 31 * result + (duration xor duration.ushr(32)).toInt()
        result = 31 * result + if (albumArtUri != null) albumArtUri!!.hashCode() else 0
        result = 31 * result + if (fileUri != null) fileUri!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Music{" +
                "title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", albumArtUri=" + albumArtUri +
                ", fileUri=" + fileUri +
                '}'
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.title)
        dest.writeString(this.album)
        dest.writeString(this.artist)
        dest.writeLong(this.duration)
        dest.writeParcelable(this.albumArtUri, 0)
        dest.writeParcelable(this.fileUri, 0)
    }

    protected constructor(`in`: Parcel) {
        this.title = `in`.readString()
        this.album = `in`.readString()
        this.artist = `in`.readString()
        this.duration = `in`.readLong()
        this.albumArtUri = `in`.readParcelable(Uri::class.java!!.getClassLoader())
        this.fileUri = `in`.readParcelable(Uri::class.java!!.getClassLoader())
    }

    companion object {

        val CREATOR: Parcelable.Creator<Music> = object : Parcelable.Creator<Music> {
            override fun createFromParcel(source: Parcel): Music {
                return Music(source)
            }

            override fun newArray(size: Int): Array<Music?> {
                return arrayOfNulls(size)
            }
        }
    }
}