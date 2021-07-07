package sin.m.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import retrofit.WeatherTransmitter
import sin.m.weatherapp.WorkManagers.MyWorker
import sin.m.weatherapp.data.examples.devColibri.DBAndroid
import sin.m.weatherapp.data.examples.devColibri.DevViewer
import sin.m.weatherapp.data.examples.startAndroid.SqlStartAndr
import sin.m.weatherapp.data.storage.sqlite.DBWeather
import sin.m.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAdd.setOnClickListener(this)
        binding.buttonClear.setOnClickListener(this)
        binding.buttonRead.setOnClickListener(this)
        binding.buttonOneMore.setOnClickListener(this)
        binding.buttonReadAll.setOnClickListener(this)
        Log.e(this.javaClass.simpleName, "ama here")
        test()

    }

    fun test() {
        val weatherTransmitter=WeatherTransmitter()
        weatherTransmitter.printWeatherINeed()
       // weatherTransmitter.useWeatherINeed { println(it) }
    }

    fun workman(){
        val  myWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }

    fun startAndrSQLITE(v: View) {
        val sqlStartAndr =
            SqlStartAndr(this)

        when (v?.id) {
            R.id.button_add -> sqlStartAndr.add()
            R.id.button_read -> sqlStartAndr.read()
            R.id.button_clear -> sqlStartAndr.clear()
            R.id.button_OneMore -> test()
        }
    }


    fun devColibriSQLITE(v: View) {
        val devViewer = DevViewer(this)
        val dbAndroid=DBAndroid(this)
        when (v?.id) {
            R.id.button_add -> devViewer.add()
            R.id.button_read -> devViewer.read(1)
            R.id.button_readAll -> devViewer.readAll()
            R.id.button_OneMore -> test()
            R.id.button_clear -> dbAndroid.printCont()
        }
    }

    fun weatherSQLiteTest(v: View){
        val dbWeather=DBWeather(this)
        when (v?.id) {
            R.id.button_readAll -> println(dbWeather.getWeatherInPeriod(dayBordersTimestamp()))
            //R.id.button_clear -> println(dbWeather.getWeatherInPeriod(dayBordersTimestamp()))
        }
    }

    override fun onClick(v: View) {
        weatherSQLiteTest(v)
    }


}

