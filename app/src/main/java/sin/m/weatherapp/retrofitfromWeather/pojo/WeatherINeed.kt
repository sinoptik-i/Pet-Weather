package retrofitfromWeather.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherINeed {
    @SerializedName("main")
    @Expose
    lateinit var main: Main

    @SerializedName("weather")
    @Expose
    lateinit var weather: List<Weather>// пиздец, лист размера 1, нахуя?

    fun getTemp() = main.temp
    fun getWeatherType() = weather[0].main
    fun getWeatherTypeDescription() = weather[0].description
    /*fun getWearherType() = weather.main
    fun getWearherTypeDescription() = weather.description*/
}