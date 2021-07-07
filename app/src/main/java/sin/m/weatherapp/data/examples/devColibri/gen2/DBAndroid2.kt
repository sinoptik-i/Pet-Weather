package sin.m.weatherapp.data.examples.devColibri.gen2

import android.content.Context
import android.database.Cursor
import sin.m.weatherapp.data.examples.devColibri.Contact
import sin.m.weatherapp.data.examples.devColibri.DBForAnyVersions
import sin.m.weatherapp.data.examples.devColibri.IDatabaseHandler
//import sin.m.weatherapp.data.examples.devColibri.query

//import sin.m.weatherapp.data.examples.devColibri.query

class DBAndroid2(context: Context) :
    DBForAnyVersions(context),IDatabaseHandler{


   override fun delAll(){
        writableDatabase.delete(TABLE_CONTACTS,null,null)
    }

    override fun addContact(contact: Contact) {
        this.writableDatabase.insert(
            TABLE_CONTACTS,
        null,
        inflateContentValues(contact))

    }

    override fun getContact(id: Int): Contact? {
        val cursor: Cursor? = this.readableDatabase.query(
            TABLE_CONTACTS, arrayOf(
                KEY_ID, KEY_NAME, KEY_PH_NO
            ),
            "$KEY_ID=?",
            arrayOf(id.toString()),
            null, null, null, null
        )

        cursor?.moveToFirst()
        return cursor?.let { inflateContact(it) }
/*
        return cursor?.getString(0)?.toInt()
            ?.let {
                Contact(
                    it, cursor?.getString(1),
                    cursor?.getString(2)
                )
            }
*/
    }



    fun test(id:Int){
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val cursor: Cursor? =this.readableDatabase.query(selectQuery, arrayOf(
            KEY_ID, KEY_NAME, KEY_PH_NO
        ),
        "$KEY_ID=?",
        arrayOf(id.toString()),
        null, null, null, null
        )
    }

    override fun getAllContacts(): List<Contact> {
        val contactList: MutableList<Contact> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val cursor=readableDatabase.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do{
                contactList.add(inflateContact(cursor))
            }
                while (cursor.moveToNext())
        }
        return contactList
    }




}