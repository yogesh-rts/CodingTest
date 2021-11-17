package com.androidprojects.personal.codingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.GridLayoutAnimationController
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidprojects.personal.codingtest.adapter.AdapterHelper
import com.androidprojects.personal.codingtest.model.Item
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener as OnItemTouchListener

class MedalCaseActivity() : AppCompatActivity() {

    private val SPAN_COUNT = 2
    private var listOfMedals = ArrayList<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enabling the activity to full screen mode
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_medal_case)

        supportActionBar?.apply {
            // Added custom text-view layout for the toolbar title
            setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
            setCustomView(R.layout.action_bar_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_ios_new_black_24dp)
        }


        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        // Setting adapter for the Recyclerview in order to display the list in form of scrollable mode with GRID layout
        recyclerView.apply {
            val medalCaseAdapter = AdapterHelper(listOfMedals)
            adapter = medalCaseAdapter
            val gridLayout = GridLayoutManager(this@MedalCaseActivity,SPAN_COUNT ,GridLayoutManager.VERTICAL, false)
            /* Setting the span count of the Grid view based on the header and grid item to be displayed
            * Based on the position and view type from adapter, setting entire row span as one for header
            * and row span as two for representing GRID for items  */
            gridLayout.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        // allocating row for displaying header title
                        0,7 -> gridLayout.spanCount
                        //allocating row as gridview for badge/medal item list
                        else -> 1
                    }
                }

            }

            layoutManager = gridLayout

        }


        loadDataList()

    }




    /*
    * Responsible for displaying the custom Menu options under the tool bar
    * for the user to navigate through menu options from the list
    * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.main_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_rewards -> {
                val intent = Intent(applicationContext,RewardActivity::class.java)
                startActivity(intent)
            }

            R.id.action_settings -> {
                val intent = Intent(applicationContext,RewardActivity::class.java)
                startActivity(intent)
            }

            R.id.action_set_goal -> {
                val intent = Intent(applicationContext,RewardActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }



    /*
    * Function loadDatalist() contains the data for each category of medal list in form of POJO class
    * Header subclass is responsible for Header title
    * GridItem subclass is responsible for types of medal to be shown in form of GRID VIEW
    *  */
    fun loadDataList(){

        // Personal Records Badge list
        listOfMedals.add(Item.GridItem(R.drawable.longest_run, "Longest Run", "00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.highest_elevation, "Highest Elevation", "2095ft"))
        listOfMedals.add(Item.GridItem(R.drawable.fastest_5k, "Fastest 5K", "00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.fastest_10k, "10K", "00:00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.fastest_half_marathon, "Half Marathon", "00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.fastest_marathon, "Marathon", "Not Yet"))

    // Virtual Records Badge list
        listOfMedals.add(Item.GridItem(R.drawable.virtual_half_marathon_race, "Virtual Half Marathon Race", "00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.virtual_half_marathon_race, "Virtual Half Marathon Race", "00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.tokyo_kakone_ekiden, "Tokyo-Hakone Ekiden 2020", "00:00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.virtual_10k_race, "Virtual 10K Race", "00:00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.hakone_ekiden, "Hakone Ekiden", "00:00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.mizuno_singapore_ekiden, "Mizuno Singapore Ekiden 2015", "00:00:00"))
        listOfMedals.add(Item.GridItem(R.drawable.virtual_5k_race, "Virtual 5K Race", "23:07"))
        listOfMedals.add(Item.GridItem(R.drawable.virtual_marathon_race, "Virtual Marathon Race", "00:00"))

    }
}