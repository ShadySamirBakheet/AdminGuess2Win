package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues
import java.util.*

class Match (
    var  matchID: Int,
    var  matchTeamOne: Int,
    var matchTeamTwo: Int,
    var matchLeague: Int,
    var matchWeek: Int,
    var matchMonth: Int,
    var matchSeason: Int,
    var matchResultTeamOne :Int,
    var  matchResultTeamTwo: Int ,
    var  matchDateStart: Date ,
    var  finsh: Boolean
) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("matchID", matchID)
        hashMap.put("matchTeamOne", matchTeamOne)
        hashMap.put("matchTeamTwo", matchTeamTwo)
        hashMap.put("matchLeague", matchLeague)
        hashMap.put("matchResultTeamOne", matchResultTeamOne)
        hashMap.put("matchResultTeamTwo", matchResultTeamTwo)
        hashMap.put("matchDateStart", matchDateStart.toString())
        hashMap.put("finsh", finsh)

        return hashMap
    }
}