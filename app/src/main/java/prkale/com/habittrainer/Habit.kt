package prkale.com.habittrainer

import android.graphics.Bitmap

/**
 * Created by prkale on 11/20/17.
 */
data class Habit(val title:String, val description:String, val image:Bitmap)

data class Habit2(val title:String, val description:String, val image:Int)

fun getSampleHabits():List<Habit2>{

    return listOf(
            Habit2("Go for walk", "nice walk in sun", R.drawable.tv),
            Habit2("Drink glass of water", "refreshing glass of water", R.drawable.tv)
    )


}