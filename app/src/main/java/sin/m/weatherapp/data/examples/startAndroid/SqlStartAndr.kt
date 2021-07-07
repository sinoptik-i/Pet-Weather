package sin.m.weatherapp.data.examples.startAndroid

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class SqlStartAndr(
    context: Context
) {
    lateinit var dbInteractor: DBInteractor
    lateinit var db: SQLiteDatabase

    init {
        dbInteractor =
            DBInteractor(context)
        db = dbInteractor.getWritableDatabase()
    }

    fun add() {
        Log.e(this.javaClass.simpleName,"ama add")
        val cv = ContentValues()

        val name: String = "example_name"
        val email: String = "example_email"


        cv.put("name", name)
        cv.put("email", email)
        // вставляем запись и получаем ее ID
        val rowID: Long = db.insert("mytable2", null, cv)
        Log.e(this.javaClass.simpleName, "row inserted, ID = $rowID")
    }


    fun read() {
        Log.e(this.javaClass.simpleName, "--- Rows in mytable: ---")
        val c: Cursor = db.query("mytable2", null, null, null, null, null, null)

        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            val idColIndex: Int = c.getColumnIndex("id")
            val nameColIndex: Int = c.getColumnIndex("name")
            val emailColIndex: Int = c.getColumnIndex("email")
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.e(
                    this.javaClass.simpleName,
                    "ID = " + c.getInt(idColIndex).toString() +
                            ", name = " + c.getString(nameColIndex).toString() +
                            ", email = " + c.getString(emailColIndex)
                )
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext())
        } else Log.e(this.javaClass.simpleName, "0 rows")
        c.close()
    }

    fun clear() {
        Log.e(this.javaClass.simpleName, "--- Clear mytable2: ---")
        // удаляем все записи
        val clearCount = db.delete("mytable2", null, null)
        Log.e(this.javaClass.simpleName, "deleted rows count = " + clearCount);
    }

}