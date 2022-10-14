package shady.samir.adminetwak3kora.Models

import android.content.ContentValues
import java.util.*

class Week(
    var  weekID: Int,
    var  weekNameAr: String,
    var  weekNameEn: String,
    var weekStart: Date,
    var weekEnd: Date
) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("weekID", weekID)
        hashMap.put("weekStart", weekStart.toString())
        hashMap.put("weekEnd", weekEnd.toString())
        hashMap.put("weekNameAr", weekNameAr)
        hashMap.put("weekNameEn", weekNameEn)


        return hashMap
    }
}