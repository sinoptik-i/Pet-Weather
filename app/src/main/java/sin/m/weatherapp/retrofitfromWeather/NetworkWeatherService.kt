package retrofit

import android.content.Context
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object NetworkWeatherService {
    private val BASE_URL = "https://api.openweathermap.org"
    private var app_id = "b1bde1ba8e7d072a163ca6aff1db67cb"

    private val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val url = chain.request()
                        .url().newBuilder()
                        .addQueryParameter("APPID", app_id)
                        .build()
                    chain.proceed(
                        chain.request().newBuilder()
                            .url(url)
                            .build()
                    )
                }.build()
            )
            //.addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun getWeather() = mRetrofit.create(WeatherPlaceHolder::class.java)

}



