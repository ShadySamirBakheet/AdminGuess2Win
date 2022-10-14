package shady.samir.adminetwak3kora.Models

import android.content.ContentValues
import java.util.*

class Season(
    var  seasonID: Int,
    var  seasonNameAr: String,
    var  seasonNameEn: String,
    var seasonStart: Date,
    var seasonEnd: Date
) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("seasonID", seasonID)
        hashMap.put("seasonNameAr", seasonNameAr)
        hashMap.put("seasonNameEn", seasonNameEn)
        hashMap.put("seasonStart", seasonStart.toString())
        hashMap.put("seasonEnd", seasonEnd.toString())

        return hashMap
    }
}