package com.example.bucketlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_item_view.*
import java.util.*

class CreateItemActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item_view)

        addItem()
    }

    private fun addItem() {
        createItemButton.setOnClickListener {
            if (!titleTextField.text.isEmpty()) {
                val title = titleTextField.text.toString()
                val desc = descTextField.text.toString()

                val id = Date().getTime()
                val newItem = Item(id,title, desc, 0)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("NEW_ITEM_ADDED", newItem)
                setResult(Activity.RESULT_OK, intent)
                this.finish()
            }
        }
    }
}