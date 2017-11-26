package prkale.com.habittrainer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.single_card.view.*

/**
 * Created by prkale on 11/26/17.
 */


class HabitsAdaptor2 (val habits: List<Habit2>): RecyclerView.Adapter<HabitsAdaptor2.HabitViewHolder2>() {


    class HabitViewHolder2(val card: View): RecyclerView.ViewHolder(card)

    override fun getItemCount() = habits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsAdaptor2.HabitViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitsAdaptor2.HabitViewHolder2(view)
    }

    override fun onBindViewHolder(holder: HabitsAdaptor2.HabitViewHolder2?, index: Int) {
        if (holder != null){
            val habit = habits[index]
            holder.card.tv_title.text = habit.title
            holder.card.tv_description.text = habit.description
            holder.card.iv_icon.setImageResource(habit.image)
        }
    }

}