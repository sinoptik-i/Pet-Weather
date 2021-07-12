package sin.m.weatherapp

import android.widget.DatePicker
import retrofitfromWeather.pojo.WeatherINeed
import sin.m.weatherapp.data.storage.sqlite.WeatherSaveble
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun outputWeatherINeed(myWeather: WeatherINeed){
    println(
        "Today temp: " +
                "${myWeather.getTemp().minus(273.15).toInt()}, " +
                "${myWeather.getWeatherType()}, " +
                "${myWeather.getWeatherTypeDescription()} "
    )
}

val formatter: DateFormat = SimpleDateFormat("dd:MM:yyyy")

fun DatePicker.dateToTimestamp1(): Long {
    val strDate = "$dayOfMonth:$month:$year"
    val date = formatter.parse(strDate)
    date.time
    val timestamp: Timestamp = Timestamp(date.time)
    return timestamp.time
}

fun DatePicker.dateToTimestamp() = formatter.parse("$dayOfMonth:$month:$year").time

fun String.toTimastamp(): Long = Timestamp(formatter.parse(this).time).time

fun Calendar.beginOfDay(){
    set(
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DATE),
        0,
        0,
        0)
}

fun Calendar.endOfDay(){
    set(
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DATE),
        23,
        59,
        59)
}

fun WeatherINeed.toWeatherSaveble()=WeatherSaveble(
    Calendar.getInstance().time.time,
    getTemp(),
    getWeatherType(),
    getWeatherTypeDescription()
)

fun  dayBordersTimestamp():Pair<Long,Long> {
    val begin = Calendar.getInstance()
    begin.beginOfDay()
    val end: Calendar = begin.clone() as Calendar
    end.endOfDay()
    return Pair(begin.time.time, end.time.time) //: Pair<Long,Long>
}

fun Long.toStringDate()=Date(this)?.toString()

/*String{
    val date=Date(this)
    return date.toString(formatter)
}*/


