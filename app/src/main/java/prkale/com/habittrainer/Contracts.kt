package prkale.com.habittrainer

import android.provider.BaseColumns

/**
 * Created by prkale on 11/21/17.
 */


val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 1 //recreate db if version changes

object HabitEntry : BaseColumns{  //object is like anonymous inner class in java
    val TABLE_NAME = "habit"
    val _ID = "id"
    val TITLE_COL = "title"
    val DESCR_COL = "description"
    val IMAGE_COL = "image"
}