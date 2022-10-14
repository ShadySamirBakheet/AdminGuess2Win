package shady.samir.adminetwak3kora.Repositories.API

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import shady.samir.adminetwak3kora.LogInSystem.model.*
import shady.samir.adminetwak3kora.Models.DataModel.*
import shady.samir.adminetwak3kora.Models.LeagueModelResponse
import shady.samir.adminetwak3kora.Models.ProfilesResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.AddMonthModel
import shady.samir.adminetwak3kora.Models.ResponseModel.AddSeasonModel
import shady.samir.adminetwak3kora.Models.ResponseModel.AddWeekModel
import shady.samir.adminetwak3kora.Models.ResponseModel.AwardsResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.MatchModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Help.AddHelpResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Help.HelpResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.LeagueWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.*
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.AddMatchRequestModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.AddMatchResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.DeleteMatchResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Matchs.MatchsResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Rewards.AddRewardResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.DeleteTeamResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamEnResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.MonthWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.SeasonWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.WeekWinnerModel

interface SportService {

    ///for test

    @GET("api/WeatherForecast")
    suspend fun Get(): Response<Admin>

    ///Team API

    @GET("api/Teams/GetTeams/en")
    suspend fun GetTeams(): Response<TeamsResponseModel>

    @GET("api/Teams/en/{id}")
    suspend fun getTeamEn(@Path("id") id: String): Response<TeamEnResponseModel>

    @GET("api/Teams/{id}")
    suspend fun getTeam(@Path("id") id: String): Response<TeamResponseModel>

    @DELETE("api/Teams/{id}")
    suspend fun DeleteTeam(@Path("id") id: Int): Response<DeleteTeamResponseModel>

    @Multipart
    @POST("api/Teams")
    suspend fun PostTeam(
        @Part("NameEn") NameEn: RequestBody,
        @Part("NameAr") NameAr: RequestBody,
        @Part ImageFile: MultipartBody.Part,
        @Part("CountryEn") CountryEn: RequestBody,
        @Part("CountryAr") CountryAr: RequestBody
    ): Response<TeamResponseModel>

    @Multipart
    @PUT("api/Teams/{id}")
    suspend fun PutTeam(
        @Path("id") id: String,
        @Part("id") TeamID: RequestBody,
        @Part("NameEn") NameEn: RequestBody,
        @Part("NameAr") NameAr: RequestBody,
        @Part ImageFile: MultipartBody.Part?,
        @Part("CountryEn") CountryEn: RequestBody,
        @Part("CountryAr") CountryAr: RequestBody
    ): Response<TeamResponseModel>

    @GET("api/Teams/GetTeamsByLeague/en/{leagueID}")
    suspend fun GetTeamsByLeague(@Path("leagueID") leagueID: String): Response<TeamsResponseModel>

    ///Priodicals League API

    @GET("api/Leagues/GetLeagues/en")
    suspend fun GetLeagues(): Response<LeaguesResponseModel>

    @GET("api/Leagues/{id}")
    suspend fun getLeagueById(@Path("id") id: String): Response<LeagueModelResponse>

    @GET("api/Leagues/{id}")
    suspend fun GetPriodical(@Path("id") id: Int): Response<League>

    @Multipart
    @PUT("api/Leagues/{id}")
    suspend fun PutPriodical(
        @Path("id") id: String,
        @Part("id") LeagueId: RequestBody,
        @Part("NameEn") NameEn: RequestBody,
        @Part("NameAr") NameAr: RequestBody,
        @Part ImageFile: MultipartBody.Part?,
        @Part("StartDate") StartDate: RequestBody,
        @Part("EndDate") EndDate: RequestBody
    ): Response<AddLeagueResponse>

    @Multipart
    @POST("api/Leagues")
    suspend fun PostPriodical(
        @Part("NameEn") NameEn: RequestBody,
        @Part("NameAr") NameAr: RequestBody,
        @Part ImageFile: MultipartBody.Part,
        @Part("StartDate") StartDate: RequestBody,
        @Part("EndDate") EndDate: RequestBody
    ): Response<AddLeagueResponse>//AddLeagueResponse

    @DELETE("Leagues/{id}")
    suspend fun DeletePriodical(@Path("id") id: Int)

    ///Matches API

    @GET("api/Matches")
    suspend fun GetMatchs(@Query("date") date: String): Response<MatchsResponseModel>

    @GET("Matches/{id}")
    suspend fun GetMatch(@Path("id") id: Int): Response<MatchModel>

    @PUT("api/Matches/{id}")
    suspend fun PutMatch(@Path("id") id: String,@Body match: AddMatchRequestModel): Response<DeleteMatchResponseModel>

    @POST("api/Matches")
    suspend fun PostMatch(@Body match: AddMatchRequestModel): Response<AddMatchResponseModel>

    @DELETE("api/Matches/{id}")
    suspend fun DeleteMatch(@Path("id") id: String): Response<DeleteMatchResponseModel>


    ///API for Score

    @GET("api/OrderByBriodicals/{id}")
    suspend fun GetOrderByBriodicals(@Path("id") id: Int): Response<LeagueWinnerModel>

    @GET("api/OrderByMonths/{id}")
    suspend fun GetOrderByMonths(@Path("id") id: Int): Response<MonthWinnerModel>

    @GET("api/OrderByWeeks/{id}")
    suspend fun GetOrderByWeeks(@Path("id") id: Int): Response<WeekWinnerModel>

    @GET("api/OrderBySeason/{id}")
    suspend fun GetOrderBySeasons(@Path("id") id: Int): Response<SeasonWinnerModel>

    /////

    @GET("api/Awards/GetAwards/en/")
    suspend fun getAwards(): Response<AwardsResponseModel>

    @GET("api/Helpers/GetHeplers/en")
    suspend fun getHelpsList(): Response<HelpResponseModel>

    @POST("api/Helpers")
    suspend fun addHelp(@Body helper: Helper): Response<AddHelpResponseModel>

    @PUT("api/Helpers/{id}")
    suspend fun updateHelp(
        @Path("id") id: String,
        @Body helper: Helper
    ): Response<AddHelpResponseModel>

    @GET("api/Helpers/{id}")
    suspend fun getHelp(@Path("id") id: String): Response<AddHelpResponseModel>

    @DELETE("api/Helpers/{id}")
    suspend fun deleteHelp(@Path("id") id: String): Response<AddHelpResponseModel>

    @POST("api/Awards/")
    suspend fun addReward(@Body award: Award): Response<AddRewardResponseModel>

    @GET("api/Awards/{id}")
    suspend fun updateReward(@Path("id") id: String): Response<AddRewardResponseModel>

    @PUT("api/Awards/{id}")
    suspend fun updateReward(
        @Path("id") id: String,
        @Body reward: AddRewardResponseModel.Data
    ): Response<AddRewardResponseModel>

    @DELETE("api/Awards/{id}")
    suspend fun deleteReward(@Path("id") id: String): Response<AddRewardResponseModel>

    @DELETE("api/Leagues/{id}")
    suspend fun deleteLeague(@Path("id") id: String): Response<DeleteLeagueResponseModel>

    @GET("api/Weeks")
    suspend fun getWeeks(): Response<List<Week>>

    @GET("api/Weeks/{id}")
    suspend fun getWeek(@Path("id") id: Int): Response<Week>

    @PUT("api/Weeks/{id}")
    suspend fun PutWeek(
        @Path("id") id: Int,
        @Body week: Week
    ): Response<Week>

    @DELETE("api/Weeks/{id}")
    suspend fun DelWeek(@Path("id") id: Int): Response<Week>

    @POST("api/Weeks")
    suspend fun addWeek(@Body weekModel: AddWeekModel): Response<Week>

    /////apis for month

    @GET("api/Months")
    suspend fun getMonths(): Response<List<Month>>

    @POST("api/Months")
    suspend fun addMonth(@Body weekModel: AddMonthModel): Response<Month>

    @GET("api/Months/{id}")
    suspend fun getMonth(@Path("id") id: Int): Response<Month>

    @PUT("api/Months/{id}")
    suspend fun PutMonth(
        @Path("id") id: Int,
        @Body month: Month
    ): Response<Month>

    @DELETE("api/Months/{id}")
    suspend fun DelMonth(@Path("id") id: Int): Response<Month>


    /////apis for Season

    @GET("api/Seasons")
    suspend fun getSeasons(): Response<List<Season>>

    @POST("api/Seasons")
    suspend fun addSeason(@Body seasonModel: AddSeasonModel): Response<Season>

    @GET("api/Seasons/{id}")
    suspend fun getSeason(@Path("id") id: Int): Response<Season>

    @PUT("api/Seasons/{id}")
    suspend fun PutSeason(
        @Path("id") id: Int,
        @Body season: Season
    ): Response<Season>

    @DELETE("api/Seasons/{id}")
    suspend fun DelSeason(@Path("id") id: Int): Response<Season>

    @GET("api/Home/PutPoint/{id}/{score1}/{score2}")
    suspend fun addMatchResult(
        @Path("id") id: String,
        @Path("score1") score1: String,
        @Path("score2") score2: String
    ): Response<Void>

    @DELETE("api/DeleteAll")
    suspend fun deleteAll(): Response<Void>

    @POST("api/Account/Register/{id}")
    suspend fun addAdmin(
        @Path("id") id: String,
        @Body addAdminRequestModel: AddAdminRequestModel
    ): Response<AddAdminResponseModel>

    @POST("api/Administration/GetAllUsers/{id}")
    suspend fun getProfiles(@Path("id") id: String): Response<ProfilesResponseModel>

    @GET
    suspend fun confirmCode(@Url callbackUrl: String?): Response<ConfirmEmailResponse>

    @POST("api/Account/Login")
    suspend fun login(@Body loginRequestModel: LoginRequestModel): Response<LoginResponseModel>

    @GET("api/Account/ResetPassword/{email}")
    suspend fun resetPassword(@Path("email") email: String): Response<ResetPassordResponseModel>

    @GET("api/Account/ValidateResetPasswordToken")
    suspend fun sendCodeForResetPassword(
        @Query("userId") userId: String?,
        @Query("randomKey") randomKey: String?
    ): Response<AddAdminResponseModel>

    @POST
    suspend fun reset(
        @Url url: String,
        @Body resetModel: ResetRequestModel?
    ): Response<ConfirmEmailResponse>

    @GET("api/Teams/AssignTeamsToLeagues/{leagueId}/{teamId}")
    suspend fun addTeamToLeague(
        @Path("leagueId") leagueId: String,
        @Path("teamId") teamId: String
    ): Response<Void>

    @DELETE("api/Teams/RemoveTeamFromLeague/{leagueId}/{teamId}")
    suspend fun deleteTeam(
        @Path("leagueId") leagueId: String,
        @Path("teamId") teamId: String
    ): Response<ConfirmEmailResponse>

    @POST("api/Account/ChangePassword/{userId}")
    suspend fun changePassword(
        @Path("userId") userId: String,
        @Body changePasswordRequestModel: ChangePasswordRequestModel
    ): Response<ConfirmEmailResponse>

    @POST("api/Administration/GetAllAdmins/{id}")
    suspend fun getAdmins(@Path("id") id: String?): Response<AdminsResponseModel>

    @GET("api/Administration/RemoveAdmin")
    suspend fun deleteAdmin(
        @Query("userId") userId: String,
        @Query("adminId") adminId: String
    ): Response<ConfirmEmailResponse>

    @DELETE("api/Administration/DeleteUser")
    suspend fun deleteUser(
        @Query("adminId") adminId: String,
        @Query("userId") userId: String
    ): Response<ConfirmEmailResponse>
}