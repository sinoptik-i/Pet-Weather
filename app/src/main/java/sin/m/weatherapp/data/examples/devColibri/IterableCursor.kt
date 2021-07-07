package sin.m.weatherapp.data.examples.devColibri

import android.database.Cursor
import android.database.CursorWrapper
import android.database.sqlite.SQLiteDatabase
import android.util.ArrayMap

class IterableCursor(private val cursor: Cursor) : CursorWrapper(cursor), Iterable<Cursor> {

    init {
        this.cursor.moveToPosition(-1)
    }

    private val cachedIndexes: MutableMap<String, Int> = ArrayMap()

    override fun getColumnIndex(columnName: String) =
        cachedIndexes.getOrPut(columnName) {
            super.getColumnIndex(columnName)
        }

    override fun getColumnIndexOrThrow(columnName: String) =
        cachedIndexes.getOrPut(columnName) {
            super.getColumnIndexOrThrow(columnName)
        }

    override fun close() {
        super.close()
        cachedIndexes.clear()
    }

    override fun iterator(): Iterator<Cursor> = CursorIterator()

    private inner class CursorIterator : Iterator<Cursor> {

        var isMovedToNext = cursor.moveToNext()

        override fun hasNext(): Boolean {
            isMovedToNext = isMovedToNext || !cursor.isClosed && cursor.moveToNext()
            return isMovedToNext
        }

        override fun next(): Cursor {
            isMovedToNext = false
            return cursor
        }
    }
}

fun Cursor.toIterable() = IterableCursor(this)

fun SQLiteDatabase.query(
    table: String? = null,
    columns: Array<String?>? = null,
    selection: String? = null,
    selectionArgs: Array<String?>? = null,
    groupBy: String? = null,
    having: String? = null,
    orderBy: String? = null,
    limit: String? = null
): Cursor? = query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)