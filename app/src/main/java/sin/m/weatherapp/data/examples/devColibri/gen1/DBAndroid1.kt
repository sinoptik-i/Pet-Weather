package sin.m.weatherapp.data.examples.devColibri.gen1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import sin.m.weatherapp.data.examples.devColibri.Contact
import sin.m.weatherapp.data.examples.devColibri.DBForAnyVersions
import sin.m.weatherapp.data.examples.devColibri.IDatabaseHandler




class DBAndroid1(context: Context) :
    DBForAnyVersions(context), IDatabaseHandler {

    override fun delAll(){
        writableDatabase.delete(TABLE_CONTACTS,null,null)
    }
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

    override fun addContact(contact: Contact) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, contact._name)
        values.put(KEY_PH_NO, contact._phone_number)
        db.insert(TABLE_CONTACTS, null, values)
        db.close()
    }

    override fun getContact(id: Int): Contact? {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(
            TABLE_CONTACTS, arrayOf(
                KEY_ID, KEY_NAME, KEY_PH_NO
            ),
            "$KEY_ID=?",
            arrayOf(id.toString()),
            null, null, null, null
        )
        // if (cursor == null) return null
        cursor?.moveToFirst()


        return cursor?.getString(0)?.toInt()
            ?.let {
                Contact(
                    it, cursor?.getString(1),
                    cursor?.getString(2)
                )
            }
    }

    override fun getAllContacts(): List<Contact> {
        val contactList: MutableList<Contact> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val contact = Contact()
                contact._id = (cursor.getString(0).toInt())
                contact._name = (cursor.getString(1))
                contact._phone_number = (cursor.getString(2))
                contactList.add(contact)
            } while (cursor.moveToNext())
        }

        return contactList
    }


}