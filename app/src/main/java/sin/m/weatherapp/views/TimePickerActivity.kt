package sin.m.weatherapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import sin.m.weatherapp.R
import sin.m.weatherapp.dateToTimestamp
import sin.m.weatherapp.dateToTimestamp1
import java.util.*

class TimePickerActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_picker)
        var tmpkr=findViewById<DatePicker>(R.id.datepicker)
        var button=findViewById<Button>(R.id.button)
        val date: Date = Date()
        date.time


        /*button.setOnClickListener(View.OnClickListener {
        })*/
        button.setOnClickListener { v: View? ->
            Log.e("TPA","${tmpkr.dateToTimestamp()}")
        }

    }
}