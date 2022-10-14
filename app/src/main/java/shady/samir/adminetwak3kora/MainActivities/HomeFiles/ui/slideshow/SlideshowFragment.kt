package shady.samir.adminetwak3kora.MainActivities.HomeFiles.ui.slideshow

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import shady.samir.adminetwak3kora.R

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    var playLink = "https://play.google.com/store/apps/dev?id=8294723716541769768"
    var faceBook = "https://m.facebook.com/profile.php?id=100014752950992"

    lateinit var image: ImageView
    lateinit var text3: TextView
    lateinit var text1:TextView
    lateinit var text4:TextView
    lateinit var text2:TextView

    var b = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        text1 = root.findViewById<TextView>(R.id.text1)
        text2 = root.findViewById<TextView>(R.id.text2)
        text3 = root.findViewById<TextView>(R.id.text3)
        text4 = root.findViewById<TextView>(R.id.text4)
        image = root.findViewById<ImageView>(R.id.image)

        text1.setOnClickListener { send() }
        text3.setOnClickListener { openLink(faceBook) }

        text4.setOnClickListener { openLink(playLink) }

        text2.setOnClickListener { openWhatsApp() }

        image.setOnClickListener { imagefill() }

        return root
    }

    private fun imagefill() {
        if (b) {
            image.layoutParams.height = 350
            b = false
        } else {
            image.layoutParams.height = 200
            b = true
        }
    }

    private fun openWhatsApp() {
        val uri = Uri.parse("smsto:" + "+201207090513")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }

    private fun openLink(Link: String) {
        val uri = Uri.parse(Link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun send() {
        val TO = arrayOf("shady.samir.1997@gmail.com")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            Log.i("Finished email...", "Finished sending email...")
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context,"There is no email client installed.",Toast.LENGTH_LONG).show()
        }
    }

}
