package sin.m.weatherapp.data.storage.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private val DATABASE_NAME = "weatherDB"
private val DATABASE_VERSION = 1

open class DBForExtentionsAndFeauchers(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    protected val TABLE_WEATHER = "weather"

    protected val KEY_ID_TIME = "time_id"
    protected val KEY_TEMP = "temp"
    protected val KEY_WEATHER_TYPE = "weatherType"
    protected val KEY_DESCR = "description"
    protected val ALL_COLUMNS:Array<String?>?= arrayOf(
        KEY_ID_TIME,KEY_TEMP,KEY_WEATHER_TYPE,KEY_DESCR
    )

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_WEATHER + "("
                + KEY_ID_TIME + " INTEGER PRIMARY KEY," + KEY_TEMP + " REAL,"
                + KEY_WEATHER_TYPE + " TEXT" + KEY_DESCR + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_WEATHER")
        onCreate(db)
    }

    protected fun contentValuesFromWeatherSaveble(weatherSaveble: WeatherSaveble): ContentValues {
        val values = ContentValues()
        values.put(KEY_ID_TIME, weatherSaveble.time_id)
        values.put(KEY_TEMP, weatherSaveble.temp)
        values.put(KEY_WEATHER_TYPE, weatherSaveble.weatherType)
        values.put(KEY_DESCR, weatherSaveble.description)
        return values
    }

    protected fun inflateWeatherSaveble(cursor: Cursor): WeatherSaveble = WeatherSaveble(
        cursor.getInt(0).toLong(),
        cursor.getDouble(1),
        cursor.getString(2),
        cursor.getString(3)
    )

}