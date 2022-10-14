package shady.samir.adminetwak3kora.SharedPreferencesData

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesData(var context: Context) {
    var sharedPreferences:SharedPreferences
        get() {
            return context.getSharedPreferences("SharedPreferences",Context.MODE_PRIVATE)
        }
        set(value) {}

    fun isUser():Boolean{
        if (sharedPreferences.getString("UserMail",null)!=null){
            return true
        }else{
            return false
        }
    }

    fun getUser():List<String>{
        val list:ArrayList<String> = ArrayList()
        sharedPreferences.getString("UserMail",null)?.let { list.add(it) }
        sharedPreferences.getString("UserPass",null)?.let { list.add(it) }

        return list
    }

    fun setUser(userEmail:String,userPass:String){
        val edit = sharedPreferences.edit()
        edit.apply {
            putString("UserMail",userEmail)
            putString("UserPass",userPass)
        }.apply()
    }

    fun delUser(){
        val edit = sharedPreferences.edit()
        edit.apply {
            remove("UserMail")
            remove("UserPass")
        }.apply()
    }
    fun setId(userId:String){
        val edit = sharedPreferences.edit()
        edit.apply {
            putString("userId",userId)
        }.apply()
    }

    fun setSignIn(){
        val edit = sharedPreferences.edit()
        edit.apply {
            putBoolean("isSignIn",true)
        }.apply()
    }

    fun setIsSuperAdmin(isSuperAdmin:Boolean){
        val edit = sharedPreferences.edit()
        edit.apply {
            putBoolean("isSuperAdmin",isSuperAdmin)
        }.apply()
    }

    fun isSuperAdmin() = sharedPreferences.getBoolean("isSuperAdmin",false)

    fun isSignIn() = sharedPreferences.getBoolean("isSignIn",false)

    fun signOut(){
        val edit = sharedPreferences.edit()
        edit.apply {
            putBoolean("isSignIn",false)
        }.apply()
    }

    fun getId() = sharedPreferences.getString("userId",null)

    fun setKey(userKey:String){
        val edit = sharedPreferences.edit()
        edit.apply {
            putString("UserKey",userKey)
        }.apply()
    }

    fun getKey(): String? {
        return sharedPreferences.getString("UserKey",null)
    }

}