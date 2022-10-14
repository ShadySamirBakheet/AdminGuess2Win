package shady.samir.adminetwak3kora.Adapters.ArrayAdapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.R
import java.util.*

class TeamArrayAdapter(val context: Context, var dataSource: List<TeamsResponseModel.Team>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.teamitam, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.txtteamname.text = dataSource.get(position).name

        Glide.with(context).load(dataSource.get(position).image)

        vh.txtteamcountry.text = dataSource.get(position).country

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val txtteamname: TextView
        val imagegteamlogo: ImageView
        val txtteamcountry:TextView

        init {
            txtteamname = row?.findViewById(R.id.txtteamname) as TextView
            imagegteamlogo = row?.findViewById(R.id.imagegteamlogo) as ImageView
            txtteamcountry = row?.findViewById(R.id.txtteamcountry)
        }
    }

}