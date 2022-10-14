package shady.samir.adminetwak3kora.Image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.*

class Image {
  private var path: String? = null
  private var context: Context? = null

  fun Image(context: Context) {
    this.context = context
    path =
      if (Build.VERSION.SDK_INT >= 17) context.applicationInfo.dataDir + "/Images/" else "/data/data/" + context.packageName + "/Images/"
    val file = File(path)
    if (!file.exists()) {
      file.mkdir()
    }
  }

  fun getimage(imageView: ImageView, imageName: String,saveName: String): String? {

    try {
      val file = File(path, "$saveName.png")
      val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
      imageView.setImageBitmap(bitmap)
      return file.path
    } catch (e: FileNotFoundException) {
      return downloadimage(imageView, imageName, saveName)
    }
  }

  private fun downloadimage(
    imageView: ImageView,
    imageName: String,
    saveName: String
  ): String? {
    context?.let {
      Glide.with(it).asBitmap().load(imageName).into(object : SimpleTarget<Bitmap>() {
        override fun onResourceReady(p0: Bitmap, p1: Transition<in Bitmap>?) {
          saveimage(saveName,p0)
          imageView.setImageBitmap(p0)
        }

      })
    }
    return null
  }

  private fun saveimage(adid: String, bitmap: Bitmap): String? {
    val f = File(path)
    val file = File(path, "$adid.png")
    val fileOutputStream: FileOutputStream
    try {
      fileOutputStream = FileOutputStream(file)
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
      return file.path
    } catch (e: FileNotFoundException) {
      e.printStackTrace()
    }
    return null
  }
}