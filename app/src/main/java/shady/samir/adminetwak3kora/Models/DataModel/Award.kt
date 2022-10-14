package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues

class Award(
    var NameAr: String,
    var NameEn: String,
    var FromPoint: Int,
    var ToPoint: Int,
    var Type: Int,
    var awardID: Int? = null
) {
//    fun contentValuesOf(): ContentValues? {
//        val hashMap = ContentValues()
//
//        hashMap.put("awardID", awardID)
//        hashMap.put("awardNameAr", awardNameAr)
//        hashMap.put("awardNameEn", awardNameEn)
//        hashMap.put("awardPoint", awardPoint)
//        hashMap.put("awardPointTo", awardPointTo)
//        hashMap.put("awardType", awardType)
//
//        return hashMap
//    }
}