package retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofitfromWeather.pojo.WeatherINeed
import sin.m.weatherapp.data.storage.sqlite.WeatherSaveble

interface WeatherPlaceHolder {
    //http://api.openweathermap.org/data/2.5/weather?q=Lipetsk,ru&APPID=b1bde1ba8e7d072a163ca6aff1db67cb

    @GET("/data/2.5/weather?APPID=b1bde1ba8e7d072a163ca6aff1db67cb")  // working!!
    fun getWeatherWithTown(@Query("q") town: String): Call<WeatherINeed>

    @GET("/data/2.5/weather?APPID=b1bde1ba8e7d072a163ca6aff1db67cb")  // working!!
    fun getWeatherWithTownRx(@Query("q") town: String): Single<WeatherINeed>

    @GET("/data/2.5/weather?}")
    fun getWeatherWithTown(@Query("q") town: String,
                           @Query("APPID") appId: String): Call<WeatherINeed?>?//Query тупо идут подряд?

    @GET("/data/2.5/weather?}")
    fun getWeatherWithTownRx(@Query("q") town: String,
                           @Query("APPID") appId: String): Single<WeatherINeed>//Query тупо идут подряд?

    @GET("/data/2.5/weather?APPID=b1bde1ba8e7d072a163ca6aff1db67cb")
    fun getWeatherWithTownAsString(@Query("q") town: String): Call<String?>?

}