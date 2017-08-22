package alimalvi.`in`.kotlinwithvolley.Adapters

import alimalvi.`in`.kotlinwithvolley.DataModels.ResponseResult
import alimalvi.`in`.kotlinwithvolley.DisplayData
import alimalvi.`in`.kotlinwithvolley.R
import alimalvi.`in`.kotlinwithvolley.Utils.Utils
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

/**
 * Created by BUGLE1 on 8/21/2017.
 */
class ForcastAdapter(private val context: Context, val arraylist: ArrayList<ResponseResult>) : RecyclerView.Adapter<ForcastAdapter.ForcastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForcastViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.inflater_datelist, parent, false)
        return ForcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForcastViewHolder?, position: Int) {

        holder?.dateTime?.text = Utils.getDateCurrentTimeZone(arraylist[0].list[position].dt)
        holder?.temperature?.text = arraylist[0].list[position].temp.day.toString() + " C"
        Picasso.with(context).load("http://openweathermap.org/img/w/"+arraylist[0].list[position].weather[0].icon+".png").fit().into(holder?.imageView)
        holder?.itemView?.setOnClickListener {
            val intent = Intent(context, DisplayData::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val arraylistData = Gson().toJson(arraylist)
            intent.putExtra("arraylist", arraylistData)
            intent.putExtra("position", position)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return arraylist[0].list.size
    }

    class ForcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTime = itemView.findViewById<RecyclerView>(R.id.inflater_datetime_textView) as TextView
        val temperature = itemView.findViewById<RecyclerView>(R.id.inflater_temperature_textView) as TextView
        val imageView=itemView.findViewById<RecyclerView>(R.id.inflater_imageView) as ImageView
    }
}


