package shady.samir.adminetwak3kora.Adapters.ArrayAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import shady.samir.adminetwak3kora.Models.Season
import shady.samir.adminetwak3kora.R
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class SeasonArrayAdapter(context: Context, seasonList: List<Season>) :
    ArrayAdapter<Season>(context, 0, seasonList) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.week_item, parent, false)
        }
        val txtWeekName = convertView!!.findViewById<TextView>(R.id.txtName)
        val txtweekStart = convertView.findViewById<TextView>(R.id.txtStart)
        val txtweekEnd = convertView.findViewById<TextView>(R.id.txtEnd)
        val season = getItem(position)
        if (season != null) {
            txtWeekName.setText(season.seasonNameAr)
            val format: Format = SimpleDateFormat("dd/MM/yyyy")
            var date: Date? = season.seasonStart
            txtweekStart.text = format.format(date)
            date = season.seasonEnd
            txtweekEnd.text = format.format(date)
        }
        return convertView
    }

    override fun getFilter(): Filter{
        return weekFilter
    }

    private val weekFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val results = FilterResults()
            val list = ArrayList<Season>()
            if (charSequence == null || charSequence.length == 0) {
                list.addAll(seasonList)
            } else {
                val prifx = charSequence.toString().toLowerCase().trim { it <= ' ' }
                for (season in seasonList) {
                    if (season.seasonNameAr.toLowerCase().contains(prifx)) {
                        list.add(season)
                    }
                }
            }
            results.values = list
            results.count = list.size
            return results
        }

        override fun publishResults(
            charSequence: CharSequence,
            filterResults: FilterResults
        ) {
            clear()
            addAll(filterResults.values as List<Season>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as Season).seasonNameAr
        }
    }

}