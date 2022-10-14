package shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse

import java.sql.Time
import java.util.*

data class MatchModel(
    val Date: Date,
    val FirstTeamId: Int,
    val Id: Int,
    val IsEnded: Boolean,
    val IsStarted: Boolean,
    val LeagueId: Int,
    val NoOfFirstTeamGoals: Int?,
    val NoOfSecondTeamGoals: Int?,
    val SecondTeamId: Int,
    val Time: Time
)