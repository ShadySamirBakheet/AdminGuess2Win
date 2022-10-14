package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues

class User(
     var userID: String,
     var  username: String,
     var  phoneNumber: String,
     var  imgUrl: String,
     var  score: Int
) {
     fun contentValuesOf(): ContentValues? {
          val hashMap = ContentValues()

          hashMap.put("username", username)
          hashMap.put("phoneNumber", phoneNumber)
          hashMap.put("imgUrl", imgUrl)
          hashMap.put("score", score)
          hashMap.put("userID", userID)

          return hashMap
     }
}