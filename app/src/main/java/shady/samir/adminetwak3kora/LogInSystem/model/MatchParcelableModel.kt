package shady.samir.adminetwak3kora.LogInSystem.model

import android.os.Parcel
import android.os.Parcelable

class MatchParcelableModel(
    var matchId: Int,
    var leagueId: Int,
    var teamOneId: Int,
    var teamTwoId: Int,
    var date: String?,
    var isStarted: Int,
    var isEnded: Int,
    var noOfGoalsTeam1: Int,
    var noOfGoalsTeam2: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(matchId)
        dest?.writeInt(leagueId)
        dest?.writeInt(teamOneId)
        dest?.writeInt(teamTwoId)
        dest?.writeString(date)
        dest?.writeInt(isStarted)
        dest?.writeInt(isEnded)
        dest?.writeInt(noOfGoalsTeam1)
        dest?.writeInt(noOfGoalsTeam2)
    }

    companion object CREATOR : Parcelable.Creator<MatchParcelableModel> {
        override fun createFromParcel(parcel: Parcel): MatchParcelableModel {
            return MatchParcelableModel(parcel)
        }

        override fun newArray(size: Int): Array<MatchParcelableModel?> {
            return arrayOfNulls(size)
        }
    }
}