package sin.m.weatherapp.WorkManagers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit.WeatherTransmitter
import sin.m.weatherapp.data.storage.sqlite.DBWeather
import java.lang.Exception
import java.util.concurrent.TimeUnit


val TAG = "Worker"

class MyWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        Log.d(TAG, "doWork: start");
        val weatherWorker = WeatherTransmitter()
        //        val dbWeather=DBWeather(context)
        val dbWeather = DBWeather(applicationContext)

        try {
            weatherWorker.useWeatherINeed(dbWeather::addWeatherSaveble)
            TimeUnit.SECONDS.sleep(10);
            weatherWorker.useWeatherINeed { dbWeather.addWeatherSaveble(it) }
        } catch (ex: Exception) {
            return Result.failure()
        }
        Log.d(TAG, "doWork: end");
        return Result.success()
    }


}