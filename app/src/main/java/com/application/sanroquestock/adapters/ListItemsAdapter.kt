package com.application.sanroquestock.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.sanroquestock.R
import com.application.sanroquestock.model.EntityItems

class ListItemsAdapter(private val itemList: MutableList<EntityItems?>) : RecyclerView.Adapter<ListItemsAdapter.ListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_for_list_recycler_stock, parent, false))


    override fun getItemCount()= itemList.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class ListHolder(view: View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name_item)
        val barcode = view.findViewById<TextView>(R.id.barcode_item)
        val cant = view.findViewById<TextView>(R.id.cant_item)
        fun bind(item: EntityItems?){
            name.text = item?.description
            barcode.text = item?.barcode
            cant.text = item?.cantidad.toString()
        }
    }
}