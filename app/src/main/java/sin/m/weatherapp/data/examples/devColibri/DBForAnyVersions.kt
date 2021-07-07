package sin.m.weatherapp.data.examples.devColibri

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private val DATABASE_NAME = "contactsManager"
private val DATABASE_VERSION = 1

open class DBForAnyVersions(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    protected val TABLE_CONTACTS = "contacts"
    protected val KEY_ID = "id"
    protected val KEY_NAME = "name"
    protected val KEY_PH_NO = "phone_number"


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    protected fun inflateContentValues(contact: Contact):ContentValues{
        val values = ContentValues()
        values.put(KEY_NAME, contact._name)
        values.put(KEY_PH_NO, contact._phone_number)
        return values
    }




    protected fun inflateContact(cursor: Cursor): Contact = Contact(
        cursor.getInt(0),
        cursor.getString(1),
        cursor.getString(2)
    )

    protected fun Cursor.getString(columnName: String): String? = getColumnIndex(columnName)
        .takeIf { it >= 0 }
        ?.let { getString(it) }
}