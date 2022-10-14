package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddWeekActivity
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.util.*


class WeekAdapter(
    var context: Context,
    var list: List<Week>
) :
    RecyclerView.Adapter<WeekAdapter.ViewHolder>() {

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
        val week: Week = list[position]
        holder.txtWeekName.text = week?.weekNameEn
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        val startDate = format.parse(week.weekStart)
        holder.txtweekStart.text = format.format(startDate!!)
        holder.txtweekEnd.text = format.format(format.parse(week.weekEnd)!!)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddWeekActivity::class.java)
            intent.putExtra("id", week.weekID)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtweekStart: TextView = itemView.findViewById(R.id.txtStart)
        var txtweekEnd: TextView = itemView.findViewById(R.id.txtEnd)
        var txtWeekName: TextView = itemView.findViewById(R.id.txtName)

    }

    init {
        this.list = list
    }
}
