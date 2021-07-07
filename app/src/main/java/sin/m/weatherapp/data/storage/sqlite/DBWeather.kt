package sin.m.weatherapp.data.storage.sqlite

import android.content.Context
import android.database.Cursor
import sin.m.weatherapp.data.storage.query
import sin.m.weatherapp.data.storage.toIterable

class DBWeather(context: Context) :
    DBForExtentionsAndFeauchers(context),
    DBHandler {
    override fun addWeatherSaveble(weatherSaveble: WeatherSaveble) {
        try {
            this.writableDatabase.insert(
                TABLE_WEATHER,
                null,
                contentValuesFromWeatherSaveble(weatherSaveble)
            )
            println("weather saved normalno!")
        }
        catch (ex:Exception){
            println(ex.message)
        }
    }



    override fun getWeatherInPeriod(interval: Pair<Long, Long>) = readableDatabase.query(
        TABLE_WEATHER,
        ALL_COLUMNS,
        "$KEY_ID_TIME>? AND $KEY_ID_TIME<?",
        arrayOf(interval.first.toString(), interval.second.toString())
    )?.use {
        it.toIterable().map {
            inflateWeatherSaveble(cursor = it)
        }
    } ?: emptyList()

/*    override fun getWeatherSaveble(id: Long) = readableDatabase.query(
        TABLE_WEATHER,
       ALL_COLUMNS,
        "$KEY_ID_TIME=?",
        arrayOf(id.toString())
    )?.use {
        it.toIterable().map { cursor ->
            inflateWeatherSaveble(cursor)
        }[0]
    }*/

    override fun getWeatherSaveble(id: Long): WeatherSaveble? {
        val cursor=readableDatabase.query(
            TABLE_WEATHER,
            ALL_COLUMNS,
            "$KEY_ID_TIME=?",
            arrayOf(id.toString())
        )
        cursor?.moveToFirst()
        return cursor?.let{inflateWeatherSaveble(it)}
    }




}