package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class MainActivity : AppCompatActivity(), ClickDetectorInterface {

    private lateinit var binding: ActivityMainBinding

    var dataToDisplay:MutableList<String> = mutableListOf()
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ---- RecyclerView -----
        // define a list of data
        this.dataToDisplay = mutableListOf("Apple", "Banana", "Carrot", "Donut")
        var numbersList:MutableList<String> = mutableListOf("1", "2", "3", "4")
        var lettersList:MutableList<String> = mutableListOf("A", "B", "C", "D")

        // create an instance of the adapter
        this.adapter = MyAdapter(dataToDisplay, this)
        binding.rv.adapter = adapter

        // required
        binding.rv.layoutManager = LinearLayoutManager(this)

        // add a line between each item in the list
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        // ---- end RecyclerView -----



        // ---- click handlers -----
        binding.btnAdd.setOnClickListener {
            val itemName:String = binding.etItemName.text.toString()
            val snackbar = Snackbar.make(binding.root, "Item to add: ${itemName}", Snackbar.LENGTH_SHORT)
            snackbar.show()

            // TODO: Code to add item to the end of the list
            dataToDisplay.add(itemName)
            adapter.notifyDataSetChanged()
        }

        binding.btnDeleteAll.setOnClickListener {
            val snackbar = Snackbar.make(binding.root, "All items deleted!", Snackbar.LENGTH_SHORT)
            snackbar.show()

            // TODO: Code to delete all items
            dataToDisplay.clear()
            adapter.notifyDataSetChanged()
        }

        binding.btnDeleteOne.setOnClickListener {
            // assumes user will always type a number
            val pos = binding.etDeletePosition.text.toString().toInt()
            val snackbar = Snackbar.make(binding.root, "Position to delete: ${pos}", Snackbar.LENGTH_SHORT)
            snackbar.show()

            // TODO: Code to delete the item in the specified position
            dataToDisplay.removeAt(pos)
            adapter.notifyDataSetChanged()
        }

        binding.btnUpdate.setOnClickListener {
            // assumes user will always type a number
            val pos = binding.etUpdatePosition.text.toString().toInt()

            // randomly generate a number between 99-150 and add it to the end of the item
            val randomNumber =  Random.nextInt(99, 150)

            // TODO: Code to update the item in the specified position
            dataToDisplay[pos] = "${dataToDisplay[pos]} - ${randomNumber}"
            adapter.notifyItemChanged(pos)

            val snackbar = Snackbar.make(binding.root, "Updating ${pos} with ${randomNumber}", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

    }

    //

    override fun myClickFunction1(position: Int) {
        val snackbar = Snackbar.make(binding.root, "You clicked button at row ${position}", Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    override fun myClickFunction2(position: Int) {
        val snackbar = Snackbar.make(binding.root, "AAAAAAA ${position}", Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    override fun rowClicked(position: Int) {
        this.dataToDisplay.removeAt(position)
        adapter.notifyDataSetChanged()
//        val snackbar = Snackbar.make(binding.root, "REMOVE... ${position}", Snackbar.LENGTH_SHORT)
//        snackbar.show()
    }
}