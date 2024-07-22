package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.utils.SnackbarHelper
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ClickDetectorInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private lateinit var snackbarHelper: SnackbarHelper
    private var fruitList:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // getting mock data
        this.fruitList = Mock.FRUIT_LIST.toMutableList()

        // create an instance of the custom adapter
        this.adapter = MyAdapter(this.fruitList, this)
        this.binding.rvContainer.adapter = this.adapter

        // init custom snackbar helper
        this.snackbarHelper = SnackbarHelper(this.binding.root)

        // configuring the RecyclerView with a LinearLayoutManager
        this.binding.rvContainer.layoutManager = LinearLayoutManager(this)

        // adding a line between each item in the list of the
        this.binding.rvContainer.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // click handlers
        this.binding.btnAddItem.setOnClickListener {
            val newItem:String = this.binding.etItemName.text.toString()

            // adding new item to the end of the list
            fruitList.add(newItem)
            this.adapter.notifyDataSetChanged()

            this.snackbarHelper.showSnackbar("Item added: $newItem")
        }

        this.binding.btnDeleteAll.setOnClickListener {
            // deleting all items
            fruitList.clear()
            this.adapter.notifyDataSetChanged()

            this.snackbarHelper.showSnackbar("All items deleted")
        }

        this.binding.btnDeleteOneItem.setOnClickListener {
            // assuming the user will always type a number
            val pos = this.binding.etDeletePositionItem.text.toString().toInt()

            // delete the item in the specified position
            fruitList.removeAt(pos)
            this.adapter.notifyDataSetChanged()

            this.snackbarHelper.showSnackbar("Deleted row: $pos")
        }

        this.binding.btnUpdate.setOnClickListener {
            // assuming the user will always type a number
            val pos = this.binding.etUpdatePositionItem.text.toString().toInt()

            // randomly generate a number between 99-150 and add it to the end of the item
            val randomNumber: Int =  Random.nextInt(100, 999)

            // update the item in the specified position
            fruitList[pos] = "$randomNumber"
            this.adapter.notifyItemChanged(pos)

            this.snackbarHelper.showSnackbar("Updated row: $pos, with: $randomNumber")
        }
    }

    override fun onPushMeBtn(position: Int) {
        this.snackbarHelper.showSnackbar("On push me button at position: $position")
    }

    override fun onClickTv(position: Int) {
        this.snackbarHelper.showSnackbar("On click text-view at position: $position")
    }

    override fun onRowClicked(position: Int) {
        this.fruitList.removeAt(position)
        this.adapter.notifyDataSetChanged()
        this.snackbarHelper.showSnackbar("On row clicked at position: $position")
    }
}