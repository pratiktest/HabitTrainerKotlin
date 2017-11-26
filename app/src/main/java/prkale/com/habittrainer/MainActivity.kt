package prkale.com.habittrainer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by prkale on 11/20/17.
 */
public class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HabitsAdaptor(HabitDbTable(this).readAllHabits())
        //rv.adapter = HabitsAdaptor2(getSampleHabits())


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_habit){
            switch(CreateHabitActivity::class.java)
        }
        return true
    }

    private fun switch(c:Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
    }


}