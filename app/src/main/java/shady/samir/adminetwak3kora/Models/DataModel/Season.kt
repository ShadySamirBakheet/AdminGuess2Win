package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues
import com.google.gson.annotations.SerializedName
import java.util.*

class Season(
    @SerializedName("id")
    var  id: Int,
    @SerializedName("startSeason")
    var  startSeason: String,
    @SerializedName("endSeason")
    var  endSeason: String,
    @SerializedName("nameAr")
    var nameAr: String,
    @SerializedName("nameEn")
    var nameEn: String
)

//{"id":1,"startSeason":"0001-01-01T00:00:00","endSeason":"0001-01-01T00:00:00","nameAr":"شششش","nameEn":"asd"}