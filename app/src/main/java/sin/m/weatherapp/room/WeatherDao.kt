package sin.m.weatherapp.room

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(entities: List<WeatherEntity>)

    @Delete
    fun delete(entities: List<WeatherEntity>)

    @Insert
    fun put(vararg entities: WeatherEntity)

    @Query("DELETE FROM WeatherEntity WHERE time_id in (:ids)")
    fun delete(vararg ids: Long)

    @Query("SELECT * FROM WeatherEntity")
    fun getAll(): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity WHERE :id = time_id LIMIT 1")
    fun getWeather(id: Long): WeatherEntity?

    @Query("SELECT * FROM WeatherEntity WHERE :from > time_id AND :to < time_id")
    fun getWeatherInterval(from: Long, to: Long): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity")
    fun trackAll(): Flowable<List<WeatherEntity>>


    @Query("SELECT * FROM WeatherEntity WHERE :from > time_id AND :to < time_id")
    fun trackWeatherInterval(from: Long, to: Long): Flowable<List<WeatherEntity>>

    @Query("DELETE FROM WeatherEntity")
    fun clearTable()

}