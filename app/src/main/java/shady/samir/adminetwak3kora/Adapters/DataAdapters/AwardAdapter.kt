package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddAwardActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.AwardsResponseModel
import shady.samir.adminetwak3kora.R
import java.util.*


class AwardAdapter(
    var context: Context,
    var list: List<AwardsResponseModel.Data>
) :
    RecyclerView.Adapter<AwardAdapter.ViewHolder>() {

    var level: List<String>? = arrayListOf(
        "المركز الاول",
        "المركز الثاني",
        "المركز الثالث",
        "المركز الرابع",
        "المركز الخامس",
        "المركز السادس",
        "المركز السابع",
        "المركز الثامن",
        "المركز التاسع",
        "المركز العاشر"
    )
    var type: List<String>? = arrayListOf("الدوري", "الموسم", "الشهر", "الاسبوع")
    private fun inti() {
        level = ArrayList()
        (level as ArrayList<String>).add("المركز الاول")
        (level as ArrayList<String>).add("المركز الثاني")
        (level as ArrayList<String>).add("المركز الثالث")
        (level as ArrayList<String>).add("المركز الرابع")
        (level as ArrayList<String>).add("المركز الخامس")
        (level as ArrayList<String>).add("المركز السادس")
        (level as ArrayList<String>).add("المركز السابع")
        (level as ArrayList<String>).add("المركز الثامن")
        (level as ArrayList<String>).add("المركز التاسع")
        (level as ArrayList<String>).add("المركز العاشر")
        (level as ArrayList<String>).add("المركز الحادي عشر")
        (level as ArrayList<String>).add("المركز الثاني عشر")
        (level as ArrayList<String>).add("المركز الثالث عشر")
        (level as ArrayList<String>).add("المركز الرابع عشر")
        (level as ArrayList<String>).add("المركز الخامس عشر")
        (level as ArrayList<String>).add("المركز السادس عشر")
        (level as ArrayList<String>).add("المركز السابع عشر")
        (level as ArrayList<String>).add("المركز الثامن عشر")
        (level as ArrayList<String>).add("المركز التاسع عشر")
        (level as ArrayList<String>).add("المركز العشرون")
        (level as ArrayList<String>).add("المركز الحادي والعشرون")
        (level as ArrayList<String>).add("المركز الثاني والعشرون")
        (level as ArrayList<String>).add("المركز الثالث والعشرون")
        (level as ArrayList<String>).add("المركز الرابع والعشرون")
        (level as ArrayList<String>).add("المركز الخامس والعشرون")
        (level as ArrayList<String>).add("المركز السادس والعشرون")
        (level as ArrayList<String>).add("المركز السابع والعشرون")
        (level as ArrayList<String>).add("المركز الثامن والعشرون")
        (level as ArrayList<String>).add("المركز التاسع والعشرون")
        (level as ArrayList<String>).add("المركز الثلاثون")
        (level as ArrayList<String>).add("المركز الحادي والثلاثون")
        (level as ArrayList<String>).add("المركز الثاني والثلاثون")
        (level as ArrayList<String>).add("المركز الثالث والثلاثون")
        (level as ArrayList<String>).add("المركز الرابع والثلاثون")
        (level as ArrayList<String>).add("المركز الخامس والثلاثون")
        (level as ArrayList<String>).add("المركز السادس والثلاثون")
        (level as ArrayList<String>).add("المركز السابع والثلاثون")
        (level as ArrayList<String>).add("المركز الثامن والثلاثون")
        (level as ArrayList<String>).add("المركز التاسع والثلاثون")
        (level as ArrayList<String>).add("المركز الاربعون")
        (level as ArrayList<String>).add("المركز الحادي والاربعون")
        (level as ArrayList<String>).add("المركز الثاني والاربعون")
        (level as ArrayList<String>).add("المركز الثالث والاربعون")
        (level as ArrayList<String>).add("المركز الرابع والاربعون")
        (level as ArrayList<String>).add("المركز الخامس والاربعون")
        (level as ArrayList<String>).add("المركز السادس والاربعون")
        (level as ArrayList<String>).add("المركز السابع والاربعون")
        (level as ArrayList<String>).add("المركز الثامن والاربعون")
        (level as ArrayList<String>).add("المركز التاسع والاربعون")
        (level as ArrayList<String>).add("المركز الخمسون")
        type = ArrayList()
        (type as ArrayList<String>).add("الدوري")
        (type as ArrayList<String>).add("الموسم")
        (type as ArrayList<String>).add("الشهر")
        (type as ArrayList<String>).add("الاسبوع")

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inti()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.award_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Log.e("level", level?.size.toString())
        val award = list[position]
        holder.txtawardname.setText(award.name)
        holder.txtpoint.text =
            "من " + level!![(award.fromPoint?.minus(1))
                ?: 0] + " الي " + level!![award.toPoint?.minus(1) ?: 0]
        holder.txttype.text = type!![award.type ?: 0]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddAwardActivity::class.java)
            intent.putExtra("id", award.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtawardname: TextView
        var txtpoint: TextView
        var txttype: TextView

        init {
            txtawardname = itemView.findViewById(R.id.txtawardname)
            txtpoint = itemView.findViewById(R.id.txtpoint)
            txttype = itemView.findViewById(R.id.txttype)
        }
    }
//
//    init {
//        inti()
//    }
}