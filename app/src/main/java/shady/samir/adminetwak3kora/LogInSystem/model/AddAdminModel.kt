package shady.samir.adminetwak3kora.LogInSystem.model

import android.os.Parcel
import android.os.Parcelable

class AddAdminModel(var randomKey: String?, var callbackUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }


    companion object CREATOR : Parcelable.Creator<AddAdminModel> {
        override fun createFromParcel(parcel: Parcel): AddAdminModel {
            return AddAdminModel(parcel)
        }

        override fun newArray(size: Int): Array<AddAdminModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(randomKey)
        parcel?.writeString(callbackUrl)
    }
}