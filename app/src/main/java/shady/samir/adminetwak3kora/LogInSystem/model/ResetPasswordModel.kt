package shady.samir.adminetwak3kora.LogInSystem.model

import android.os.Parcel
import android.os.Parcelable

class ResetPasswordModel(var userId:String?,var randomKey:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(userId)
        dest?.writeString(randomKey)
    }

    companion object CREATOR : Parcelable.Creator<ResetPasswordModel> {
        override fun createFromParcel(parcel: Parcel): ResetPasswordModel {
            return ResetPasswordModel(parcel)
        }

        override fun newArray(size: Int): Array<ResetPasswordModel?> {
            return arrayOfNulls(size)
        }
    }
}