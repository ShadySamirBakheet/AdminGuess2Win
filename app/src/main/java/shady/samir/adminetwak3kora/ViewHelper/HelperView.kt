package shady.samir.adminetwak3kora.ViewHelper

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import shady.samir.adminetwak3kora.R

class HelperView {

    fun toastShow(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

    fun toastAShow(context: Context,activity: Activity,msg: String,icon:Int){
        val inflater = activity.layoutInflater
        val view: View = inflater.inflate(
           R.layout.toast_layout,activity.findViewById<ViewGroup>(R.id.toast_view)
        )

        val ToastText = view.findViewById<TextView>(R.id.ToastText)
        val toast_image =
            view.findViewById<ImageView>(R.id.toast_image)

        ToastText.text = msg
        toast_image.setImageResource(icon)

        val toast = Toast(context)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view

        toast.show()

    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }




    fun statusCode(context: Context,activity: Activity,code:Int){

    }

    fun statusCodeIsSccess(code:Int):Boolean{
        val sccess:ArrayList<Int> = ArrayList()
        sccess.add(200)
        sccess.add(201)
        sccess.add(202)
        sccess.add(302)

        return sccess.contains(code)
    }

}