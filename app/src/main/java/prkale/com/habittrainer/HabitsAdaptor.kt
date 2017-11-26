package prkale.com.habittrainer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.single_card.view.*

/**
 * Created by prkale on 11/20/17.
 */

class HabitsAdaptor (val habits: List<Habit>):RecyclerView.Adapter<HabitsAdaptor.HabitViewHolder>(){

    class HabitViewHolder(val card: View): RecyclerView.ViewHolder(card)

    override fun getItemCount() = habits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder?, index: Int) {
        if (holder != null){
            val habit = habits[index]
            holder.card.tv_title.text = habit.title
            holder.card.tv_description.text = habit.description
            holder.card.iv_icon.setImageBitmap(habit.image)
        }
    }


}