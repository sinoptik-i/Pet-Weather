package sin.m.weatherapp.data.storage.sqlite

interface DBHandler {
    fun addWeatherSaveble(weatherSaveble: WeatherSaveble)
    fun getWeatherSaveble(id:Long):WeatherSaveble?
    //fun getYesterdayWeather(timeBeginOfDay:Long):List<WeatherSaveble>
    fun getWeatherInPeriod(interval:Pair<Long,Long>):List<WeatherSaveble>
    /* gives the weather for every hour starting from  timeBeginOfDay */

  //  fun getAllYesterdayWeather(time:Long)
}