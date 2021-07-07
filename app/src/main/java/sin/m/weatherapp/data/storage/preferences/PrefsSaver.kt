package sin.m.weatherapp.data.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import sin.m.weatherapp.BuildConfig.APPLICATION_ID


class PrefsSaver(context: Context) {

    private val PREFS_NAME: String = APPLICATION_ID + ".prefs"
    private val LAST_NOTIFICATION_TIME = "LAST_NOTIFICATION_TIME"
    private val SNILS = "SNILS"
    var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    /*  fun PreferencesManager(context: Context) { // context - activity
          preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
      }*/

    fun addHoursTemp(time: Long, temp: Double) {
        preferences!!.edit().putFloat(time.toString(), temp.toFloat())
    }

    fun getHourTemp(time: Long): Double {
        return preferences!!.getLong(time.toString(), 0L).toDouble()
    }

    fun getMiddleTemp(time: Long): Double {
        return 0.0
    }




}



