package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues

class TeamLeague (
    var leagueID: Int,
    var teamID: Int
) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("teamID", teamID)
        hashMap.put("leagueID", leagueID)

        return hashMap
    }
}