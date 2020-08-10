package com.lamine.notepad

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 *Created by Lamine MESSACI on 09/08/2020.
 */
data class Note(
    var title: String? = "",
    var text: String? = "",
    var filename: String? = ""): Parcelable, Serializable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeString(filename)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        private val serialVersionUUID:Long = 424242424242
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}