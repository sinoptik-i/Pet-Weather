package retrofit

import android.util.Log
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofitfromWeather.pojo.WeatherINeed
import sin.m.weatherapp.data.storage.sqlite.WeatherSaveble
import sin.m.weatherapp.outputWeatherINeed
import sin.m.weatherapp.toWeatherSaveble
import kotlin.math.floor
import kotlin.math.roundToInt

class WeatherTransmitter {
    private var town = "Lipetsk,ru"

    fun useWeatherINeed(func: (myWeather: WeatherSaveble) -> Unit) {
        NetworkWeatherService.getWeather()
            .getWeatherWithTownRx(town)
            .subscribe(
                {
                    func(it.toWeatherSaveble())
                },
                {  })
    }

    fun printWeatherINeed() {
        println("printWeatherINeed")
        NetworkWeatherService.getWeather()
            .getWeatherWithTownRx(town)
            .subscribe(
                {
                    outputWeatherINeed(it)
                },
                {
                    Log.e("EEE","",it)
                })
    }


}