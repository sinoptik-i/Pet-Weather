package sin.m.weatherapp.data.examples.startAndroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBInteractor(
    context: Context
) : SQLiteOpenHelper(context, "myDB", null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        Log.e(this.javaClass.simpleName, "--- onCreate database ---")
        db.execSQL(
            "create table mytable2(id integer primary key autoincrement, name text, email text,qwe text);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}