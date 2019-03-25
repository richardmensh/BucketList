package com.example.bucketlist

import android.support.v7.widget.RecyclerView
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup

class RecyclerAdapter(var items : List<Item>, val context: Context, val listener: (Long) -> Unit) : RecyclerView.Adapter<ItemViewHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int ): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cellForRow = layoutInflater.inflate(R.layout.item_view_cell, p0, false)

        return ItemViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int ) {
        holder.bind(items[position], position, listener)
    }

    public fun swapedList(newList: List<Item>) {
        items = newList
        if (newList != null) {
            this.notifyDataSetChanged()
        }

    }


}