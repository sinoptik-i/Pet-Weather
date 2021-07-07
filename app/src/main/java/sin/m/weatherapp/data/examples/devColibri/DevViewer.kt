package sin.m.weatherapp.data.examples.devColibri

import android.content.Context
import sin.m.weatherapp.data.examples.devColibri.gen1.DBAndroid1
import sin.m.weatherapp.data.examples.devColibri.gen2.DBAndroid2
import java.util.*
import kotlin.collections.ArrayList

class DevViewer(
    context: Context,
    var versionNum: Int = 1
) {
    var versions: MutableList<IDatabaseHandler>

    init {
        versions = ArrayList()
        versions.add(DBAndroid1(context))
        versions.add(DBAndroid2(context))
    }

    fun add() {
        val date = Date()
        val contact = Contact(_name = "name ${date.time%100}", _phone_number = "$date.time")
        versions[versionNum].addContact(contact)
    }

    fun readAll() {
        versions[versionNum].getAllContacts().forEach { println(it) }
    }

    fun read(id: Int) {
        versions[versionNum].getContact(id)?.let { println(it) }
    }
    fun del(){
        versions[versionNum].delAll()
    }

}