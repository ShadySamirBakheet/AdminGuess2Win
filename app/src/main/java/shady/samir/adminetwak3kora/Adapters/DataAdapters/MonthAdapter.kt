package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddMonthActivity
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.util.*

class MonthAdapter(
    var context: Context,
   var list: List<Month>
) :
    RecyclerView.Adapter<MonthAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.week_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val week: Month = list[position]
        holder.txtWeekName.text =  week.monthNameEn
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        holder.txtweekStart.text = format.format(format.parse(week.monthStart)!!)
        holder.txtweekEnd.text = format.format(format.parse(week.monthEnd)!!)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddMonthActivity::class.java)
            intent.putExtra("id", week.monthID)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtweekStart: TextView
        var txtweekEnd: TextView
        var txtWeekName: TextView

        init {
            txtweekStart = itemView.findViewById(R.id.txtStart)
            txtweekEnd = itemView.findViewById(R.id.txtEnd)
            txtWeekName = itemView.findViewById(R.id.txtName)
        }
    }

    init {
        this.list = list
    }
}

