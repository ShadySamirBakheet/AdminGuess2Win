package shady.samir.adminetwak3kora.Adapters.ArrayAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import shady.samir.adminetwak3kora.Models.Week
import shady.samir.adminetwak3kora.R
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class WeekArrayAdapter(context: Context, weekList:List<Week>) :
    ArrayAdapter<Week>(context, 0, weekList) {


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
        val week = getItem(position)
        if (week != null) {
            txtWeekName.setText(week.weekNameAr)
            val format: Format = SimpleDateFormat("dd/MM/yyyy")
            txtweekStart.text = format.format(week.weekStart)
            txtweekEnd.text = format.format(week.weekEnd)
        }
        return convertView
    }

    override fun getFilter(): Filter {
        return weekFilter
    }

    private val weekFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val results = FilterResults()
            val list = ArrayList<Week>()
            if (charSequence == null || charSequence.length == 0) {
                list.addAll(weekList)
            } else {
                val prifx = charSequence.toString().toLowerCase().trim { it <= ' ' }
                for (week in weekList) {
                    if (week.weekNameAr.toLowerCase().contains(prifx)) {
                        list.add(week)
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
            addAll(filterResults.values as List<Week>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as Week).weekNameAr
        }
    }

}