package com.vkarmaedu.vkarma.dataModels

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Attendance(val id : String, val name : String, val present : Boolean) : Parcelable{
    constructor(parcel: Parcel) : this(parcel.readString() ?: "", parcel.readString() ?: "", parcel.readByte() != 0.toByte())
    constructor() : this("", "", false)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeByte(if (present) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attendance> {
        override fun createFromParcel(parcel: Parcel): Attendance {
            return Attendance(parcel)
        }

        override fun newArray(size: Int): Array<Attendance?> {
            return arrayOfNulls(size)
        }

    }
}