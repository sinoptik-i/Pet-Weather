package sin.m.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import io.reactivex.schedulers.Schedulers
import retrofit.WeatherTransmitter
import sin.m.weatherapp.WorkManagers.MyWorker
import sin.m.weatherapp.data.examples.devColibri.DBAndroid
import sin.m.weatherapp.data.examples.devColibri.DevViewer
import sin.m.weatherapp.data.storage.sqlite.DBWeather
import sin.m.weatherapp.databinding.ActivityMainBinding
import sin.m.weatherapp.room.AppDatabase
import sin.m.weatherapp.room.WeatherDao
import sin.m.weatherapp.room.WeatherEntity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private val weatherDao: WeatherDao by lazy { AppDatabase.getDatabase(this).weatherDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        //  test()
        //this.contentResolver.query()

        /* weatherDao.trackAll()
             .distinctUntilChanged()
             .subscribeOn(Schedulers.io())
             .subscribe({
                 Log.e("EEE","$it")
             },{})*/

    }

    fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAdd.setOnClickListener(this)
        binding.buttonClear.setOnClickListener(this)
        binding.buttonRead.setOnClickListener(this)
        binding.buttonOneMore.setOnClickListener(this)
        binding.buttonReadAll.setOnClickListener(this)
        binding.buttonToday.setOnClickListener(this)
    }

    fun test() {
        val weatherTransmitter = WeatherTransmitter()
        weatherTransmitter.printWeatherINeed()
        // weatherTransmitter.useWeatherINeed { println(it) }
    }

    fun workman() {
        val myWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }


    fun devColibriSQLITE(v: View) {
        val devViewer = DevViewer(this)
        val dbAndroid = DBAndroid(this)
        when (v?.id) {
            R.id.button_add -> devViewer.add()
            R.id.button_read -> devViewer.read(1)
            R.id.button_readAll -> devViewer.readAll()
            R.id.button_OneMore -> test()
            R.id.button_clear -> dbAndroid.printCont()
        }
    }

    fun weatherSQLiteTest(v: View) {

        val dbWeather = DBWeather(this)
        when (v?.id) {
            R.id.button_add -> workman()
            //R.id.button_read -> println(dbWeather.getWeatherSaveble())
            R.id.button_readAll -> println(
                dbWeather.getAllWeather().map { "${it.time_id.toStringDate()}, ${it.weatherType}  \n" })
            R.id.button_clear -> dbWeather.clearTable()
            R.id.button_today -> println(
                dbWeather.getWeatherInPeriod(dayBordersTimestamp())
                    .map { it.time_id.toStringDate() })
        }
    }


    fun weatherRoomTest(v: View) {
        val dbWeather = DBWeather(this)
        when (v?.id) {
            R.id.button_add -> {
                val weatherWorker = WeatherTransmitter()
                weatherWorker.useWeatherINeed { resp ->
                    weatherDao.put(
                        WeatherEntity(
                            time_id = resp.time_id,
                            temp = resp.temp,
                            weatherType = resp.weatherType,
                            description = resp.description
                        )
                    )
                }
            }
            R.id.button_readAll -> println(dbWeather.getAllWeather())
            R.id.button_clear -> Schedulers.io().scheduleDirect {
                weatherDao.clearTable()
            }
            //R.id.button_clear -> println(dbWeather.getWeatherInPeriod(dayBordersTimestamp()))
        }
    }

    override fun onClick(v: View) {
//        weatherRoomTest(v)
        weatherSQLiteTest(v)
        //devColibriSQLITE(v)
    }


}

