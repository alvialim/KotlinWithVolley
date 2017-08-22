package alimalvi.`in`.kotlinwithvolley

import alimalvi.`in`.kotlinwithvolley.DataModels.ResponseResult
import alimalvi.`in`.kotlinwithvolley.Utils.Utils
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_data.*

class DisplayData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)
        val getData=intent.getStringExtra("arraylist")
        val position=intent.getIntExtra("position",0)


        val turnsType = object : TypeToken<ArrayList<ResponseResult>>() {}.type
        val responseResult = Gson().fromJson<ArrayList<ResponseResult>>(getData, turnsType)


        Picasso.with(applicationContext).load("http://openweathermap.org/img/w/"+responseResult[0].list[position].weather[0].icon+".png").into(weather_icon)

        date.text= Utils.getDateCurrentTimeZone(responseResult[0].list[position].dt)
        weather_main.text=responseResult[0].list[position].weather[0].main
        humidity.text=responseResult[0].list[position].humidity.toString()

        day.text=responseResult[0].list[position].temp.day.toString()+" C"
        evening.text=responseResult[0].list[position].temp.eve.toString()+" C"
        morning.text=responseResult[0].list[position].temp.morn.toString()+" C"
        night.text=responseResult[0].list[position].temp.night.toString()+" C"

    }


}


