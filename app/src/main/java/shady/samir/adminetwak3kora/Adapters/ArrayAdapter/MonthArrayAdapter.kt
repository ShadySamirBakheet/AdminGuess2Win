package shady.samir.adminetwak3kora.Adapters.ArrayAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import shady.samir.adminetwak3kora.Models.Month
import shady.samir.adminetwak3kora.R
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class MonthArrayAdapter(context: Context, monthList: List<Month>) :
    ArrayAdapter<Month>(context, 0,monthList) {


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
        val month = getItem(position)
        if (month != null) {
            txtWeekName.setText(month.monthNameAr)
            val format: Format = SimpleDateFormat("dd/MM/yyyy")
            var date: Date? = month.monthStart
            txtweekStart.text = format.format(date)
            date = month.monthEnd
            txtweekEnd.text = format.format(date)
        }
        return convertView
    }

    override fun getFilter(): Filter{
        return monthFilter
    }

    private val monthFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val results = FilterResults()
            val list = ArrayList<Month>()
            if (charSequence == null || charSequence.length == 0) {
                list.addAll(monthList)
            } else {
                val prifx = charSequence.toString().toLowerCase().trim { it <= ' ' }
                for (month in monthList) {
                    if (month.monthNameAr.toLowerCase().contains(prifx)) {
                        list.add(month)
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
            addAll(filterResults.values as List<Month>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as Month).monthNameAr
        }
    }

}