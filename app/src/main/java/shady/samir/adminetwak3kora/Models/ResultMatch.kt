package shady.samir.adminetwak3kora.Models

import android.os.Parcel
import android.os.Parcelable

class ResultMatch(var firstTeam:String?,var secondTeam:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(firstTeam)
        parcel?.writeString(secondTeam)
    }

    companion object CREATOR : Parcelable.Creator<ResultMatch> {
        override fun createFromParcel(parcel: Parcel): ResultMatch {
            return ResultMatch(parcel)
        }

        override fun newArray(size: Int): Array<ResultMatch?> {
            return arrayOfNulls(size)
        }
    }
}