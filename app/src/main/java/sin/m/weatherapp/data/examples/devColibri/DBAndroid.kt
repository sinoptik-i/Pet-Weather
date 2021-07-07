package sin.m.weatherapp.data.examples.devColibri

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.ContactsContract


class DBAndroid(context: Context) :
    DBForAnyVersions(context),
    IDatabaseHandler {

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
        if (cursor == null) return null
        cursor.moveToFirst()
        ContactsContract.Contacts.PHOTO_FILE_ID

        db.query("")

        val photoFileIdIndex = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_FILE_ID)
        cursor.getString(photoFileIdIndex)
        cursor.getString("_name")

        return cursor?.getString(0)?.toInt()
            ?.let { Contact(it, cursor?.getString(1), cursor?.getString(2)) }
    }

    /*  override fun getAllContacts(): List<Contact> {
          val contactList: List<Contact> = ArrayList()
          return contactList
      }
  */
    override fun delAll() {
        TODO("Not yet implemented")
    }


    private fun test() {
        //getContacts().subscribe
    }
//Курсор - это набор строк в табличном виде

    override fun getAllContacts() = readableDatabase.query(
         TABLE_CONTACTS,
         arrayOf(
            KEY_ID, KEY_NAME, KEY_PH_NO
        )
        ,
         "$KEY_ID > ?",
        arrayOf("5".toString())
    )?.use {
        it.toIterable().map { cursor ->
            inflateContact(cursor)
        }
    } ?: emptyList()


    var q="SELECT * FROM $TABLE_CONTACTS WHERE $KEY_ID BETWEEN 3 AND 6"
    var selection:String ="$KEY_ID > ? AND $KEY_ID < ?"
//    var selection:String ="$KEY_ID > ? AND $KEY_ID < ?"


    fun getAnyContacts() = readableDatabase.query(
        table = TABLE_CONTACTS,
        columns = arrayOf(
            KEY_ID, KEY_NAME, KEY_PH_NO
        ),
        selection =    selection,
        selectionArgs =  arrayOf( "6","10")
//              selectionArgs =  arrayOf( "6")
    )?.use {
        it.toIterable().map { cursor ->
            inflateContact(cursor)
        }
    }


    fun printCont(){
        getAnyContacts()?.map { contact ->
            println(contact)
        }
    }


}