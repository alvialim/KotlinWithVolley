package alimalvi.`in`.kotlinwithvolley.DataModels

/**
 * Created by BUGLE1 on 8/21/2017.
 */
data class Forecast(val dt: Long,
                    val temp: Temperature,
                    val pressure: Float,
                    val humidity: Int,
                    val weather: List<Weather>,
                    val speed: Float,
                    val deg: Int,
                    val clouds: Int)