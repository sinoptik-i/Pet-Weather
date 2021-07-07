package sin.m.weatherapp.data

import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object TimeConverter{


//    val formatter: DateFormat = SimpleDateFormat("dd:MM:yyyy")
    val formatter: DateFormat by lazy {  SimpleDateFormat("dd:MM:yyyy") }
/*
    fun birthdayDate(): Date? {
        val timestamp = Timestamp(123)
        return Date(timestamp.time)
    }
*/
fun dayBorders(){//}:Pair<Long,Long> {
    val date: Date = Date()
    val a=date.time/86400*86400
    val tm=Timestamp(a)
    val bDate=Date(a)

    val netDate = Date(tm.time)
    println("bDate: $bDate, netDate: $netDate")

    //return sdf.format(netDate)
    println(date.time)
}


    fun stringToTimestamp(strDate:String):Long{
        val date = formatter.parse(strDate)
        val timestamp: Timestamp = Timestamp(date.time)
        return timestamp.time
    }

 /*   val timestamp:Timestamp by lazy {

    }*/

}