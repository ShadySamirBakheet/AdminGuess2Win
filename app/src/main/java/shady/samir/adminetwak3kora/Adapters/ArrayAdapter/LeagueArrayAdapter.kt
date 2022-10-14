package shady.samir.adminetwak3kora.Adapters.ArrayAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import shady.samir.adminetwak3kora.AddActivities.AddMatchActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class LeagueArrayAdapter(val dataSource: List<LeaguesResponseModel.League>, var context: Context) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.league_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        val format0 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.getDefault())

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val startDate = format0.parse(dataSource.get(position)?.startDate)
        val endDate = format0.parse(dataSource.get(position)?.endDate)
        vh.txtleauguename.text = dataSource.get(position).name

        Glide.with(context).load(dataSource.get(position).image)

        vh.txtstaertdate.text = format.format(startDate)
        vh.txtenddate.text = format.format(endDate)
        vh.btnAddTeams.hide()

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
        val txtleauguename: TextView
        val imagegleaguelogo: ImageView
        val txtstaertdate:TextView
        val txtenddate:TextView
        val btnAddTeams: FloatingActionButton

        init {
            txtleauguename = row?.findViewById(R.id.txtleauguename) as TextView
            imagegleaguelogo = row?.findViewById(R.id.imagegleaguelogo) as ImageView
            txtstaertdate = row?.findViewById(R.id.txtstaertdate)
            txtenddate = row?.findViewById(R.id.txtenddate)
            btnAddTeams = row?.findViewById(R.id.btnAddTeams)
        }
    }

}