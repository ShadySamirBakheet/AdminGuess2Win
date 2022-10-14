package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues
import com.google.gson.annotations.SerializedName
import java.util.*

class Month(
   @SerializedName("id")
   var monthID: Int,
   @SerializedName("nameAr")
   var monthNameAr: String,
   @SerializedName("nameEn")
   var monthNameEn: String,
   @SerializedName("startMonth")
   var monthStart: String,
   @SerializedName("endMonth")
   var  monthEnd: String
)