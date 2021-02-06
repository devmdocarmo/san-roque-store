package com.application.sanroquestock.view.ui.itemsStore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.sanroquestock.R
import com.application.sanroquestock.adapters.ListItemsAdapter
import com.application.sanroquestock.model.EntityItems
import com.application.sanroquestock.repositories.ItemsDatabase
import com.application.sanroquestock.utils.Constanst
import com.application.sanroquestock.utils.Constanst.RESULT_OK
import com.application.sanroquestock.view.BarcodeScanActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list_items.*
import java.lang.reflect.Type

class ItemsStoreFragment : Fragment() {
    var itemsDatabase: ItemsDatabase?= null
    private val type: Type = object : TypeToken<MutableList<EntityItems?>>() {}.type
    private val typeEntity: Type = object : TypeToken<EntityItems>() {}.type
    private var itemList = mutableListOf<EntityItems?>(EntityItems(id = 0,
            barcode = "1",
            cantidad = 1,
            description = 1.toString(),
            price = 1),
            EntityItems(id = 0,
                    barcode = 2.toString(),
                    cantidad = 2,
                    description = 2.toString(),
                    price = 2),
            EntityItems(id = 0,
                    barcode = 3.toString(),
                    cantidad = 3,
                    description = 3.toString(),
                    price = 3))
    val gson = Gson()
    var listAdapter: ListItemsAdapter?= null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?= inflater.inflate(R.layout.fragment_list_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsDatabase = ItemsDatabase.getAppDataBase(view.context, "admin")
        itemsDatabase?.itemsDao()?.getAll()?.observe(viewLifecycleOwner,
        Observer {
            itemList.addAll(it)
        })
        listAdapter = ListItemsAdapter(itemList)
        charge_recycler(null)

        button_add_new_item.setOnClickListener {
            val intent = Intent(activity, BarcodeScanActivity::class.java)
            intent.putExtra(Constanst.LIST_ITEMS_TO_STRING, gson.toJson(itemList))
            startActivityForResult(intent, RESULT_OK)
        }
    }

    fun charge_recycler(nuevoItem : EntityItems?){
        if (nuevoItem!=null){
            itemList.add(nuevoItem)
            list_items.adapter?.notifyDataSetChanged()
        }
        list_items.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RESULT_OK){

            Log.d("ITEM_ADDED", data?.data.toString())
            charge_recycler(gson.fromJson(data?.data.toString(), typeEntity))

        }
    }
}