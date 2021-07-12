package sin.m.weatherapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class WeatherEntity(
    @PrimaryKey
    var time_id: Long = 0,
    var temp: Double = 0.0,
    val weatherType: String? = null,
    val description: String? = null
)