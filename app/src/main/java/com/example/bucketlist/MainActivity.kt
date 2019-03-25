package com.example.bucketlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.Executors
import java.nio.file.Files.delete
import android.view.MotionEvent
import android.view.GestureDetector





class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var items: List<Item> = ArrayList()
    private var adapter: RecyclerAdapter? = null
    private var executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        db = AppDatabase.getAppDataBase(this)
        bucketRecyclerView.layoutManager = LinearLayoutManager(this)


        getItems()

        fab.setOnClickListener { view ->
            val intent = Intent(this, CreateItemActivity::class.java)
            this.startActivityForResult(intent, 5 )
        }
    }

    private fun getItems() {
        executor.execute {
            items = db!!.itemDAO().getItems()
            runOnUiThread { updateUI() }
        }
    }

    private fun insertItem(item: Item) {

        executor.execute {
            db!!.itemDAO().insertItem(item)
            getItems()
        }

    }


    private fun updateItem(item: Item) {

        executor.execute {
            db!!.itemDAO().updateItem(item)
            getItems()
        }

    }

    private fun deleteItem(item: Item) {

        executor.execute {
            db!!.itemDAO().deleteItem(item)
            getItems()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5) {
            if (resultCode ==  Activity.RESULT_OK) {
                val  item: Item = data!!.getParcelableExtra("NEW_ITEM_ADDED")

                insertItem(item)
            }
        }

    }

    private fun deleteAllItems(items: List<Item>) {
        executor.execute {
            db!!.itemDAO().deleteItem(items)
            getItems()
        }
    }

    private fun updateUI() {
//        items = db!!.itemDAO().getItems()
        if (adapter == null) {

            adapter = RecyclerAdapter(items, this, listener = {
                for(item in items) {
                    if(item.id == it) {
                        deleteItem(item)
                    }
                }
            })
            bucketRecyclerView.adapter = adapter
        } else {
            adapter!!.swapedList(items)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        val id = item.itemId

        if (id == R.id.action_delete_item) {
            deleteAllItems(items)
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}
