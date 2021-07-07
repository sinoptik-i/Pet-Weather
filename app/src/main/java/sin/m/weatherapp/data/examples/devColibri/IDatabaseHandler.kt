package sin.m.weatherapp.data.examples.devColibri

import android.database.Cursor

interface IDatabaseHandler {
    fun addContact(contact: Contact)
    fun getContact(id: Int): Contact?
    fun getAllContacts(): List<Contact>
    fun delAll()
   /* fun getContactsCount(): Int
    fun updateContact(contact: Contact?): Int
    fun deleteContact(contact: Contact?)
    fun deleteAll()*/

}