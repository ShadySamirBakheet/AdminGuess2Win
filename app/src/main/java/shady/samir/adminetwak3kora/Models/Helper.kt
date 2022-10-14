package shady.samir.adminetwak3kora.Models

import android.content.ContentValues

class Helper(
    var helperID:Int,
    var helperTitleAr: String,
    var helperTitleEn: String,
    var helperAr: String,
    var helperEn: String) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("helperID", helperID)
        hashMap.put("helperTitleAr", helperTitleAr)
        hashMap.put("helperTitleEn", helperTitleEn)
        hashMap.put("helperAr", helperAr)
        hashMap.put("helperEn", helperEn)

        return hashMap
    }
}