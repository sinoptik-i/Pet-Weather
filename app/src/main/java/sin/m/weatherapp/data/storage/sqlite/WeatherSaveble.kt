package sin.m.weatherapp.data.storage.sqlite

data class WeatherSaveble (
    var time_id: Long = 0,
    var temp: Double = 0.0,
    val weatherType: String? = null,
    val description: String? = null
)