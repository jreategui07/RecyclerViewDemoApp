package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.RowLayoutBinding

// 1. MyAdapter constructor parameter: Setting the list of data to display and interface
class MyAdapter(val myItems:MutableList<String>, val clickInterface:ClickDetectorInterface) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // 2. ViewHolder constructor parameter: Setting the layout to use
    // Configure the binding variable for the row_layout.xml file
    inner class ViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder (binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 3. Getting the number of items in the list
    override fun getItemCount(): Int {
        return myItems.size
    }

    // 4. Getting the data that should go in the corresponding layout’s UI elements for each row
    // The “holder” parameter provides access to the row layout’s binding variable
    // The “position” parameter indicates which item in the data source we creating a row for
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("", "Creating UI for row #$position")
        // 4a. Get the current item from the list of data
        // This is a String because the data source is a list of Strings
        // For example: If the data source was a list of Student objects, then use currData:Student

        val icon = if (position <= 2) "ic_android_black_24" else "baseline_check_24"
        val myImageId:Int = holder.itemView.context.resources.getIdentifier(icon, "drawable", holder.itemView.context.packageName)
        holder.binding.ivIcon.setImageResource(myImageId)

        // 4b. Use the holder variable to populate the <TextView> in the row_layout.xml file
        val currData: String = this.myItems[position]
        holder.binding.tvTitle.text = currData

        holder.binding.btnPushMe.setOnClickListener {
            clickInterface.onPushMeBtn(position)
        }
        holder.binding.tvClickMe.setOnClickListener {
            clickInterface.onClickTv(position)
        }
        holder.binding.parentLayout.setOnClickListener {
            clickInterface.onRowClicked(position)
        }
    }
}
