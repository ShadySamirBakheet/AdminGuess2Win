package shady.samir.adminetwak3kora.TestByDB
//
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.widget.Toast
//import shady.samir.adminetwak3kora.Models.*
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class DBHelper(val context: Context?) : SQLiteOpenHelper(
//    context,"db.db",
//    null,2) {
//    var DB_NAME = "db.db"
//    var DB_VERSION = 2
//
//    private val mDataBase: SQLiteDatabase? = null
//    private val mNeedUpdate = false
//
//    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
//        val table ="create table Award( awardID varchar(50) primary key, awardNameAr varchar(50),awardNameEn varchar(50),awardPoint int , awardPointTo int,awardType int)"
//        var table1 = "create table Helper(helperID varchar(50) primary key, helperTitleAr varchar(50),helperTitleEn varchar(50),helperAr varchar(50),helperEn varchar(50));";
//        val table2 = "create table League(leagueID varchar(50) primary key, leagueNameAr varchar(50),leagueNameEn varchar(50),leagueLogo varchar(50),leagueNumTeam int,leagueStarDate date,leagueEndDate date);"
//        val table3 = "create table Matchl(matchID varchar(50) primary key, matchTeamOne varchar(50),matchTeamTwo varchar(50),matchLeague varchar(50),matchResultTeamOne int,matchResultTeamTwo int,matchDateStart date,finsh bool);"
//        val table4 = "create table Monthl(monthID varchar(50) primary key, monthNameAr varchar(50),monthNameEn varchar(50),monthStart date,monthEnd date);"
//        val table5 = "create table Season(seasonID varchar(50) primary key, seasonNameAr varchar(50),seasonNameEn varchar(50),seasonStart date,seasonEnd date);"
//        val table6 = "create table Team(teamID varchar(50) primary key, teamNameAr varchar(50),teamNameEn varchar(50),teamLogo varchar(50),teamCountryAr varchar(50),teamCountryEn varchar(50));"
//        val table7 = "create table TeamLeague(leagueID varchar(50) , teamID varchar(50));"
//        val table8 = "create table User(id varchar(50) primary key, username varchar(50),phoneNumber varchar(50),imgUrl varchar(50),score int);"
//        val table9 = "create table Week(weekID varchar(50) primary key, weekNameAr varchar(50),weekNameEn varchar(50),weekStart date,weekEnd date);"
//
//        sqLiteDatabase.execSQL(table)
//        sqLiteDatabase.execSQL(table1)
//        sqLiteDatabase.execSQL(table2)
//        sqLiteDatabase.execSQL(table3)
//        sqLiteDatabase.execSQL(table4)
//        sqLiteDatabase.execSQL(table5)
//        sqLiteDatabase.execSQL(table6)
//        sqLiteDatabase.execSQL(table7)
//        sqLiteDatabase.execSQL(table8)
//        sqLiteDatabase.execSQL(table9)
//    }
//
//    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
//        sqLiteDatabase.execSQL("drop table if exists Award")
//        sqLiteDatabase.execSQL("drop table if exists Helper")
//        sqLiteDatabase.execSQL("drop table if exists League")
//        sqLiteDatabase.execSQL("drop table if exists Matchl")
//        sqLiteDatabase.execSQL("drop table if exists Monthl")
//        sqLiteDatabase.execSQL("drop table if exists Season")
//        sqLiteDatabase.execSQL("drop table if exists Team")
//        sqLiteDatabase.execSQL("drop table if exists TeamLeague")
//        sqLiteDatabase.execSQL("drop table if exists User")
//        sqLiteDatabase.execSQL("drop table if exists Week")
//        onCreate(sqLiteDatabase)
//    }
//
//    fun selectTeamOne(id: String): List<Team>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<Team> = ArrayList()
//
//        try {
//            val cursor = db.rawQuery("select * from Team where teamID = "+id, null)
//            if (cursor == null||cursor.count == 0) {
//                return null
//            } else {
//                //weeks = MutableList<League>()
//                cursor.moveToFirst()
//                do {
//                    val item = Team(cursor.getString(cursor.getColumnIndex("teamID")),
//                        cursor.getString(cursor.getColumnIndex("teamNameAr")),
//                        cursor.getString(cursor.getColumnIndex("teamNameEn")),
//                        cursor.getString(cursor.getColumnIndex("teamLogo")),
//                        cursor.getString(cursor.getColumnIndex("teamCountryAr")),
//                        cursor.getString(cursor.getColumnIndex("teamCountryEn")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks?.toList()
//    }
//
//    fun selectTeam(): List<Team>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<Team> = ArrayList()
//
//        try {
//            val cursor = db.rawQuery("select * from Team", null)
//            if (cursor == null||cursor.count == 0) {
//                return null
//            } else {
//                //weeks = MutableList<League>()
//                cursor.moveToFirst()
//                do {
//                    val item = Team(cursor.getString(cursor.getColumnIndex("teamID")),
//                        cursor.getString(cursor.getColumnIndex("teamNameAr")),
//                        cursor.getString(cursor.getColumnIndex("teamNameEn")),
//                        cursor.getString(cursor.getColumnIndex("teamLogo")),
//                        cursor.getString(cursor.getColumnIndex("teamCountryAr")),
//                        cursor.getString(cursor.getColumnIndex("teamCountryEn")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//            Toast.makeText(context,e.message,Toast.LENGTH_LONG)
//        }
//        db.close()
//
//        Toast.makeText(context,"s = "+weeks.size,Toast.LENGTH_LONG)
//
//        return weeks?.toList()
//    }
//
//    fun selectLeagueOne(id: String): List<League>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<League> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from League where leagueID = "+id, null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = League(cursor.getString(cursor.getColumnIndex("leagueID")),
//                        cursor.getString(cursor.getColumnIndex("leagueNameAr")),
//                        cursor.getString(cursor.getColumnIndex("leagueNameEn")),
//                        cursor.getString(cursor.getColumnIndex("leagueLogo")),
//                        cursor.getInt(cursor.getColumnIndex("leagueNumTeam")),
//                        Date(cursor.getLong(cursor.getColumnIndex("leagueStarDate"))),
//                        Date(cursor.getLong(cursor.getColumnIndex("leagueEndDate"))))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectLeague(): List<League>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<League> = ArrayList()
//
//        try {
//            val cursor = db.rawQuery("select * from League", null)
//            if (cursor == null||cursor.count == 0) {
//                return null
//            } else {
//                //weeks = MutableList<League>()
//                cursor.moveToFirst()
//                do {
//                    val item = League(cursor.getString(cursor.getColumnIndex("leagueID")),
//                        cursor.getString(cursor.getColumnIndex("leagueNameAr")),
//                        cursor.getString(cursor.getColumnIndex("leagueNameEn")),
//                        cursor.getString(cursor.getColumnIndex("leagueLogo")),
//                        cursor.getInt(cursor.getColumnIndex("leagueNumTeam")),
//                        Date(cursor.getLong(cursor.getColumnIndex("leagueStarDate"))),
//                        Date(cursor.getLong(cursor.getColumnIndex("leagueEndDate"))))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks?.toList()
//    }
//
//    fun selectTeamLeague(id:String): List<TeamLeague>? {
//        val db = this.writableDatabase
//         var weeks : MutableList<TeamLeague> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from TeamLeague where leagueID = "+id, null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = TeamLeague(cursor.getString(cursor.getColumnIndex("leagueID")),
//                        cursor.getString(cursor.getColumnIndex("teamID")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectTeamLeagueteam(id:String): List<TeamLeague>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<TeamLeague> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from TeamLeague where teamID = "+id, null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = TeamLeague(cursor.getString(cursor.getColumnIndex("leagueID")),
//                        cursor.getString(cursor.getColumnIndex("teamID")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectUserOne(id:String): List<User>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<User> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from User where id = "+id, null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = User(cursor.getString(cursor.getColumnIndex("id")),
//                        cursor.getString(cursor.getColumnIndex("username")),
//                        cursor.getString(cursor.getColumnIndex("phoneNumber")),
//                        cursor.getString(cursor.getColumnIndex("imgUrl")),
//                        cursor.getInt(cursor.getColumnIndex("score")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectUser(): List<User>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<User> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from User", null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = User(cursor.getString(cursor.getColumnIndex("id")),
//                        cursor.getString(cursor.getColumnIndex("username")),
//                        cursor.getString(cursor.getColumnIndex("phoneNumber")),
//                        cursor.getString(cursor.getColumnIndex("imgUrl")),
//                        cursor.getInt(cursor.getColumnIndex("score")))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectWeekOne(week: String): List<Week>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<Week> = ArrayList()
//        try {
//            val cursor = db.rawQuery("select * from Week where weekID = "+week, null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = Week(cursor.getString(cursor.getColumnIndex("weekID")),
//                        cursor.getString(cursor.getColumnIndex("weekNameAr")),
//                        cursor.getString(cursor.getColumnIndex("weekNameEn")),
//                        Date(cursor.getLong(cursor.getColumnIndex("weekStart"))),
//                        Date(cursor.getLong(cursor.getColumnIndex("weekEnd"))))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    fun selectWeek(): List<Week>? {
//        val db = this.writableDatabase
//        var weeks : MutableList<Week> = ArrayList()
//        try {
//           val cursor = db.rawQuery("select * from Week", null)
//            if (cursor == null) {
//                return null
//            } else {
//                cursor.moveToFirst()
//                do {
//                    val item = Week(cursor.getString(cursor.getColumnIndex("weekID")),
//                        cursor.getString(cursor.getColumnIndex("weekNameAr")),
//                        cursor.getString(cursor.getColumnIndex("weekNameEn")),
//                        Date(cursor.getLong(cursor.getColumnIndex("weekStart"))),
//                        Date(cursor.getLong(cursor.getColumnIndex("weekEnd"))))
//                    weeks.add(item)
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//        }
//        db.close()
//
//        return weeks
//    }
//
//    //add funs
//    fun addWeek(award: Week) {
//        val db = this.writableDatabase
//        db.insert("Week", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addUser(award: User) {
//        val db = this.writableDatabase
//        db.insert("User", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addTeamLeague(award: TeamLeague) {
//        val db = this.writableDatabase
//        db.insert("TeamLeague", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addTeam(award: Team) {
//        val db = this.writableDatabase
//        db.insert("Team", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addSeason(award: Season) {
//        val db = this.writableDatabase
//        db.insert("Season", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addMonth(award: Month) {
//        val db = this.writableDatabase
//        db.insert("Monthl", null, award.contentValuesOf())
//        db.close()
//    }
//
//
//    fun addMatch(award: Match) {
//        val db = this.writableDatabase
//        db.insert("Matchl", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addLeague(award: League) {
//        val db = this.writableDatabase
//        db.insert("League", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addHelper(award: Helper) {
//        val db = this.writableDatabase
//        db.insert("Helper", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun addAwad(award: Award) {
//        val db = this.writableDatabase
//        db.insert("Award", null, award.contentValuesOf())
//        db.close()
//    }
//
//    fun updateLeague(award: League) {
//        val db = this.writableDatabase
//        db.update("League", award.contentValuesOf(), "leagueID = ?", arrayOf(award.leagueID))
//        db.close()
//    }
//
//    fun updateTeam(award: Team) {
//        val db = this.writableDatabase
//        db.update("Team", award.contentValuesOf(), "teamID = ?", arrayOf(award.teamID))
//        db.close()
//    }
//
//}