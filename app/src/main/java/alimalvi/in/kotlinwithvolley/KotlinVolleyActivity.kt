package alimalvi.`in`.kotlinwithvolley

import alimalvi.`in`.kotlinwithvolley.Adapters.ForcastAdapter
import alimalvi.`in`.kotlinwithvolley.DataModels.ResponseResult
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_kotlin_volley.*
import org.json.JSONException

class KotlinVolleyActivity : AppCompatActivity() {
    private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=eb43dbc1c58ea88f9dec74bcb712a0f7&q=94043&mode=json&units=metric&cnt=7"
    private var pd: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_volley)
        pd = ProgressDialog(this@KotlinVolleyActivity)
        makeVolleyRequest(URL)

    }

    private fun makeVolleyRequest(url: String) {
        pd!!.setMessage("Please Wait")
        pd!!.show()
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    try {
                        val responseResult = Gson().fromJson(response, ResponseResult::class.java)
                        val ResponseArrayList = ArrayList<ResponseResult>()
                        title_textView.text = responseResult.city.name + ", " + responseResult.city.country
                        ResponseArrayList.add(responseResult)
                        displayData(ResponseArrayList)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { })

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }

    private fun displayData(responseArrayList: ArrayList<ResponseResult>) {
        pd!!.dismiss()
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        val adapter = ForcastAdapter(applicationContext, responseArrayList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
