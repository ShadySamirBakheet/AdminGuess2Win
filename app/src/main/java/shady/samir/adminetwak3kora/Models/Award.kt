package shady.samir.adminetwak3kora.Models

import android.content.ContentValues

class Award(
   var awardID:Int,
   var        awardNameAr:String,
   var      awardNameEn:String,
   var       awardPoint:Int,
   var       awardPointTo:Int,
   var       awardType:Int) {
    fun contentValuesOf(): ContentValues? {
        val hashMap = ContentValues()

        hashMap.put("awardID", awardID)
        hashMap.put("awardNameAr", awardNameAr)
        hashMap.put("awardNameEn", awardNameEn)
        hashMap.put("awardPoint", awardPoint)
        hashMap.put("awardPointTo", awardPointTo)
        hashMap.put("awardType", awardType)

        return hashMap
    }
}