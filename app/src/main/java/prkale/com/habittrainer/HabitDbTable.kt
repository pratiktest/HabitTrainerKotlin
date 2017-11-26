package prkale.com.habittrainer

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream

/**
 * Created by prkale on 11/21/17.
 */
class HabitDbTable(context: Context){

    private val dbhelper = HabitTrainerDb(context)
    private val TAG = HabitDbTable::class.java.simpleName

    fun store(habit: Habit) : Long{
        val db = dbhelper.writableDatabase
        val values = ContentValues()
        /*values.put(HabitEntry.TITLE_COL, habit.title)
        values.put(HabitEntry.IMAGE_COL, toByteArray(habit.image))
        values.put(HabitEntry.DESCR_COL, habit.description)*/

        with(values){

            put(HabitEntry.TITLE_COL, habit.title)
            put(HabitEntry.IMAGE_COL, toByteArray(habit.image))
            put(HabitEntry.DESCR_COL, habit.description)

        }


        /*val id = db.transaction {
            db.insert(HabitEntry.TABLE_NAME, null, values)
        }*/

        val id = db.transaction {
            insert(HabitEntry.TABLE_NAME, null, values)
        }

        Log.d(TAG, "stored new habit ${habit}")
        return id
    }

    private fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }


    fun readAllHabits(): List<Habit>{

        val columns = arrayOf(HabitEntry._ID, HabitEntry.TITLE_COL, HabitEntry.DESCR_COL, HabitEntry.IMAGE_COL)
        val db = dbhelper.readableDatabase
        val order = "${HabitEntry._ID} ASC"
        val cursor = db.doQuery(HabitEntry.TABLE_NAME, columns, orderBy = order)
        val habits = mutableListOf<Habit>()
        while(cursor.moveToNext()){
            val title = cursor.getString(cursor.getColumnIndex(HabitEntry.TITLE_COL))
            val description = cursor.getString(cursor.getColumnIndex(HabitEntry.DESCR_COL))
            val byteArray = cursor.getBlob(cursor.getColumnIndex(HabitEntry.IMAGE_COL))
            val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
            habits.add(Habit(title, description, bitmap))
        }

        cursor.close()

        return habits
    }

    private fun SQLiteDatabase.doQuery(table: String, columns: Array<String>, selection: String? = null,
                                       selectionArgs: Array<String>? = null, groupBy: String? = null, having: String? = null,
                                       orderBy: String? = null): Cursor{

        return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
    }


    //extension function skeleton
    /*private fun SQLiteDatabase.transaction(function: () -> Unit){

        beginTransaction()
        try{
            function()
            setTransactionSuccessful()
        }finally {
            endTransaction()
        }
        close()
    }*/

    //hey this extension function only takes SQLiteDatabase
    /*private fun SQLiteDatabase.transaction(function: (SQLiteDatabase) -> Unit){

        beginTransaction()
        try{
            function(this)
            setTransactionSuccessful()
        }finally {
            endTransaction()
        }
        close()
    }*/

    //Hey this extension function only takes a extension function for SQLiteDatabase
    /*private fun SQLiteDatabase.transaction(function: SQLiteDatabase.() -> Unit) {

        beginTransaction()
        try {
            function()
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
        close()
    }*/

    private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {

        beginTransaction()
        val result =  try {
            val returnValue =  function()
            setTransactionSuccessful()
            returnValue
        } finally {
            endTransaction()
        }
        close()

        return result
    }
}