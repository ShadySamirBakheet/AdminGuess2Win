package shady.samir.adminetwak3kora.Models.DataModel

import android.content.ContentValues

class Team(
     var teamID: Int,
     var teamNameAr: String,
     var teamNameEn: String,
     var teamLogo: String,
     var teamCountryAr: String,
     var teamCountryEn: String
) {
     fun contentValuesOf(): ContentValues? {
          val hashMap = ContentValues()

          hashMap.put("teamID", teamID)
          hashMap.put("teamNameAr", teamNameAr)
          hashMap.put("teamNameEn", teamNameEn)
          hashMap.put("teamCountryEn", teamCountryEn)
          hashMap.put("teamLogo", teamLogo)
          hashMap.put("teamCountryAr", teamCountryAr)

          return hashMap
     }
}

/*  public int ID { get; set; }
        public string NameEn { get; set; }
        public string NameAR { get; set; }
        public string Image { get; set; }*/