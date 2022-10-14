package shady.samir.adminetwak3kora.Models

import android.content.ContentValues
import java.util.*

class Month(
   var monthID: Int,
   var monthNameAr: String,
   var monthNameEn: String,
   var monthStart: Date,
   var  monthEnd: Date
) {
   fun contentValuesOf(): ContentValues? {
      val hashMap = ContentValues()

      hashMap.put("monthID", monthID)
      hashMap.put("monthNameAr", monthNameAr)
      hashMap.put("monthNameEn", monthNameEn)
      hashMap.put("monthStart", monthStart.toString())
      hashMap.put("monthEnd", monthEnd.toString())

      return hashMap
   }
}