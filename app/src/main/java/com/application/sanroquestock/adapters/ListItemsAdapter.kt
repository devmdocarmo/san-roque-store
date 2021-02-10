package com.application.sanroquestock.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.application.sanroquestock.R
import com.application.sanroquestock.model.EntityItems
import com.application.sanroquestock.utils.Constant
import com.application.sanroquestock.view.BarcodeScanActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListItemsAdapter(private val itemList: MutableList<EntityItems?>, private val context : Context?) : RecyclerView.Adapter<ListItemsAdapter.ListHolder>() {

    private val typeEntity: Type = object : TypeToken<EntityItems>() {}.type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_for_list_recycler_stock, parent, false))


    override fun getItemCount()= itemList.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(itemList[position])
        holder.layoutCard.setOnClickListener{
            val send = EntityItems(barcode = holder.barcode.text.toString(),
                    description = holder.name.text.toString(),
                    price = itemList[position]?.price,
                    cantidad = holder.cant.text.toString().toInt())
            val intent = Intent(context, BarcodeScanActivity::class.java)
            intent.putExtra(Constant.ITEM_TO_EDIT_IN_STRING, Gson().toJson(send, typeEntity))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }
    }

    class ListHolder(view: View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name_item)
        val barcode = view.findViewById<TextView>(R.id.barcode_item)
        val cant = view.findViewById<TextView>(R.id.cant_item)
        val layoutCard = view.findViewById<ConstraintLayout>(R.id.card_container)

        fun bind(item: EntityItems?){
            name.text = item?.description
            barcode.text = item?.barcode
            cant.text = item?.cantidad.toString()
        }
    }
}