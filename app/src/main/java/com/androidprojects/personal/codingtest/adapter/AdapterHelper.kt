package com.androidprojects.personal.codingtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidprojects.personal.codingtest.R
import com.androidprojects.personal.codingtest.model.Item

class AdapterHelper(
    private var medalsList: List<Item>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER_ONE: Int = 0
    private val TYPE_ITEM: Int = 1
    private val TYPE_HEADER_TWO: Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*
        * Multiple View type is used inorder to inflate appropriate view based on the condition being checked
        * */
        return when (viewType) {
            TYPE_HEADER_ONE -> {
                val headerOneView = LayoutInflater.from(parent.context).inflate(
                    R.layout.header_personal_record,
                    parent,
                    false
                )
                ItemViewHolder.ViewHolderHeaderPersonalRecord(headerOneView)
            }
            TYPE_ITEM -> {
                val gridListView = LayoutInflater.from(parent.context).inflate(
                    R.layout.list_view,
                    parent,
                    false
                )
                ItemViewHolder.ViewHolderList(gridListView)
            }
            TYPE_HEADER_TWO -> {
                val headerSecondView = LayoutInflater.from(parent.context).inflate(
                    R.layout.header_virtual_races,
                    parent,
                    false
                )
                ItemViewHolder.ViewHolderHeaderVirtualRaces(headerSecondView)
            }
            else -> {
                throw RuntimeException("Issue inflating layout")
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){

            is ItemViewHolder.ViewHolderHeaderPersonalRecord ->
                when (position) {
                    0 -> holder.personalRecordHeader.text = "Personal Records"
                }


            is ItemViewHolder.ViewHolderHeaderVirtualRaces ->
                when (position) {
                    7 -> holder.virtualRacesHeader.text = "Virtual Races"
                }


            is ItemViewHolder.ViewHolderList -> {
                // positon-1 is used as a start position for grid items by subtracting the header row
                holder.medalName.text = (medalsList[position - 1] as Item.GridItem).medalName
                holder.runTimer.text = (medalsList[position - 1] as Item.GridItem).runTimer
                holder.imageResource.setImageResource((medalsList[position - 1] as Item.GridItem).imageResource)

                //Animating grid list items in recyclerview when loading the achievement activity
                holder.imageResource.animate()
                    .scaleX(1.0f).scaleY(1.0f) //scale to quarter(half x,half y)
                    .rotation(1080f) // three round turns
                    .setDuration(1000) // all take 1 seconds



                // Short message is displayed when click event is handled after user click on the badge
                holder.imageResource.setOnClickListener(View.OnClickListener {

                 Toast.makeText(holder.imageResource.context,
                         "Hey let's go and earn ${holder.medalName.text} badge",
                         Toast.LENGTH_LONG).show()
             })

                // disabling the 6th element (badge) from the personal records category as per the requirement
                if (medalsList[position - 1] == medalsList[5]) {
                    holder.imageResource.alpha = 0.37f
                    holder.medalName.alpha = 0.37f
                    holder.runTimer.alpha = 0.37f
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return medalsList.size + 1
    }


    //Returns the viewType (Header or Grid item)
    override fun getItemViewType(position: Int): Int {
        return when (position){
            0 -> TYPE_HEADER_ONE
            7 -> TYPE_HEADER_TWO
            else -> TYPE_ITEM
        }
    }




/*
* @Class ViewHolderList is responsible for holding the grid list view items and its meta data into recyclerview
* @Class ViewHolderHeader is responsible for holding the header title items and its meta data into recyclerview
*
* Multiple Viewtype is used inorder to display the two different view types

* */

    sealed class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        class ViewHolderList(itemView: View): ItemViewHolder(itemView){

            var imageResource: ImageView = itemView.findViewById(R.id.imageView)
            var medalName: TextView = itemView.findViewById(R.id.medal)
            var runTimer: TextView = itemView.findViewById(R.id.timer)

        }

        class ViewHolderHeaderPersonalRecord(itemView: View): ItemViewHolder(itemView){

            var personalRecordHeader: TextView = itemView.findViewById(R.id.header_title)
        }

        class ViewHolderHeaderVirtualRaces(itemView: View) : ItemViewHolder(itemView) {

            var virtualRacesHeader: TextView = itemView.findViewById(R.id.header_title_second)
        }
    }







}


