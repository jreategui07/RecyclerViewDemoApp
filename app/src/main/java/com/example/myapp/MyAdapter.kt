package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.RowLayoutBinding


// 1. MyAdapter constructor parameter: What is the list of data you want to display?
class MyAdapter(val myItems:MutableList<String>, val clickInterface:ClickDetectorInterface) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // 2. ViewHolder constructor parameter: What is the layout you should use?
    // Configure the binding variable for the row_layout.xml file
    inner class ViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder (binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 3. How many items are in the list of data?
    override fun getItemCount(): Int {
        return myItems.size
    }


    // 4. For each row, what data should go in the corresponding layout’s UI elements?
    // The “holder” parameter provides access to the row layout’s binding variable
    // The “position” parameter indicates which item in the data source we creating a row for
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("", "JLOG - Creating UI for row #${position}")
        // 4a. Get the current item from the list of data
        // This is a String because the data source is a list of Strings
        // If the data source was a list of Student objects, then use currData:Student
        val currData: String = this.myItems[position]
        val positionData: String = "#${position + 1}"

        Log.d("", "JLOG - currData: ${currData}")

        if (position == 1 || position == 2 || position == 3) {
            val myImageId:Int = holder.itemView.context.resources.getIdentifier("baseline_check_24", "drawable", holder.itemView.context.packageName)
            holder.binding.imageView.setImageResource(myImageId)
        } else {
            val myImageId:Int = holder.itemView.context.resources.getIdentifier("ic_android_black_24", "drawable", holder.itemView.context.packageName)
            holder.binding.imageView.setImageResource(myImageId)
        }

        // 4b. Use the holder variable to populate the <TextView> in the row_layout.xml file
        holder.binding.tvRowLine1.text = currData

        //

        holder.binding.btnPushMe.setOnClickListener {
            clickInterface.onPushMeBtn(position)
        }
        holder.binding.tvRowLine2.setOnClickListener {
            clickInterface.onClickTv(position)
        }
        holder.binding.parentLayout.setOnClickListener {
            clickInterface.onRowClicked(position)
        }
    }
}
