package sin.m.weatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sin.m.weatherapp.BuildConfig

@Database(
    entities = [
        WeatherEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var _database: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = _database?.takeIf { it.isOpen }
            ?: let { createDatabase(context).also { _database = it } }

        fun createDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.APPLICATION_ID + "db_weather"
        ).fallbackToDestructiveMigration()
            .build()
    }
}