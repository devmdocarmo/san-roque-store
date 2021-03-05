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
import com.application.sanroquestock.utils.Constant
import com.application.sanroquestock.view.BarcodeScanActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list_items.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Type


class ItemsStoreFragment : Fragment() {
    var itemsDatabase: ItemsDatabase?= null
    lateinit var itemIncoming : EntityItems
    private var listIncoming = mutableListOf<EntityItems>()
    private val type: Type = object : TypeToken<MutableList<EntityItems?>>() {}.type
    private val typeEntity: Type = object : TypeToken<EntityItems>() {}.type
    private var itemList = mutableListOf<EntityItems?>()
    val gson = Gson()
    var listAdapter: ListItemsAdapter?= null
    val newItems = mutableListOf<EntityItems>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?= inflater.inflate(R.layout.fragment_list_items, container, false)

    override fun onResume() {
        super.onResume()
        val tr = activity?.supportFragmentManager?.beginTransaction()
        tr?.replace(R.id.nav_host_fragment, this)
        tr?.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsDatabase = ItemsDatabase.getAppDataBase(view.context, "admin")
        itemsDatabase?.itemsDao()?.getAll()?.observe(viewLifecycleOwner,
        Observer {
            itemList.addAll(it)
            listAdapter = ListItemsAdapter(itemList, activity?.applicationContext)
            charge_recycler(null)
        })
        charge_recycler(null)

        button_add_new_item.setOnClickListener {
            val intent = Intent(activity, BarcodeScanActivity::class.java)
            intent.putExtra(Constant.LIST_ITEMS_TO_STRING, gson.toJson(itemList))
            intent.putExtra(Constant.USE_TRUE, true)
            startActivityForResult(intent, Constant.RESULT_OK)
        }

        button_update_item_list.setOnClickListener {
            GlobalScope.launch{
                itemsDatabase?.itemsDao()?.insertAll(if(newItems.isEmpty())listIncoming else newItems )
            }
            val toastNotify = Toast.makeText(activity,  "Base de Datos Acualizada!", Toast.LENGTH_SHORT)
            toastNotify.setGravity(Gravity.TOP,0,0)
            toastNotify.show()
            button_update_item_list.visibility = View.INVISIBLE
        }
    }

    private fun charge_recycler(nuevoItem : EntityItems?){
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
        if(resultCode == Constant.RESULT_OK){
            button_update_item_list.visibility = View.VISIBLE
            Log.d("ITEM_ADDED", data?.data.toString())
            itemIncoming = gson.fromJson(data?.data.toString(), typeEntity)
            charge_recycler(gson.fromJson(data?.data.toString(), typeEntity))
            newItems.add(itemIncoming)
        }else if (resultCode == Constant.RESULT_ALTERATED){
            button_update_item_list.visibility = View.VISIBLE
            listIncoming = gson.fromJson(data?.data.toString(), type)
            Log.d("ITEM_ALTERATED","correct")
            itemList.clear()
            itemList.addAll(listIncoming)
            list_items.adapter?.notifyDataSetChanged()
        }
    }
}