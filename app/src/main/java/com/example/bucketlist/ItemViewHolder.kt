package com.example.bucketlist

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.add_item_view.view.*
import kotlinx.android.synthetic.main.item_view_cell.view.*



class ItemViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item, pos: Int ,listener: (Long) -> Unit) = with(itemView) {

        view.itemNameLabel.text = item.name
        view.itemDescLabel.text = item.description
        view.item_checkBox.setOnClickListener { view ->
            // handle clicks here
            if(item.completed == 0) {
                itemNameLabel.setPaintFlags(itemNameLabel.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                itemDescLabel.setPaintFlags(itemDescLabel.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                item_checkBox.setChecked(true)
                item.completed = 1
            } else {
                itemNameLabel.setPaintFlags(itemNameLabel.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                itemDescLabel.setPaintFlags(itemDescLabel.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                item_checkBox.setChecked(false)
                item.completed = 0
            }

        }
        view.setOnLongClickListener {
            listener(item.id)
            true
        }
    }

}