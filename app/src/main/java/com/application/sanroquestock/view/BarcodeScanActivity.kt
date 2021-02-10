package com.application.sanroquestock.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.application.sanroquestock.R
import com.application.sanroquestock.model.BaseActivity
import com.application.sanroquestock.model.EntityItems
import com.application.sanroquestock.utils.Constant
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_barcode_scan.*


class BarcodeScanActivity : BaseActivity() {

    private lateinit var savedList: MutableList<EntityItems?>
    private var newitemadded :EntityItems?=null
    private val gson = Gson()
    private var newItem: EntityItems?= null
    private var stringIntent: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scan)
        if (intent.getBooleanExtra(Constant.USE_TRUE, false)) {
            stringIntent = intent.getStringExtra(Constant.LIST_ITEMS_TO_STRING)
            savedList = gson.fromJson<MutableList<EntityItems?>>(stringIntent, type)
        }
        useScaner(intent.getBooleanExtra(Constant.USE_TRUE, false))

        button_save_new_item.setOnClickListener {

            newitemadded = EntityItems(barcode = text_show_barcode.text.toString(),
                description = text_show_description.text.toString(),
                price = text_show_price.text.toString().toInt(),
                cantidad = text_show_cant.text.toString().toInt())
            
            val intentResult = Intent()
            intentResult.data = Uri.parse(gson.toJson(newitemadded, typeEntity))
            setResult(Constant.RESULT_OK, intentResult)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    private fun logicBarcodeScanned(result: IntentResult){
        for(item in savedList) {
            item?.barcode?.let {
                Log.d("ITEM_POSITION", "|$item|")
                if (it.contains(result.contents)) {
                    val toast = Toast.makeText(this@BarcodeScanActivity, "ya existe este item!", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP,0,0)
                    toast.show()
                    Log.d("ITEM_POSITION", item.barcode.toString()+" == "+ result.contents)
                    setResult(Constant.RESULT_FAILED, null)
                    finish()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                text_show_barcode.text = result.contents
                logicBarcodeScanned(result)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}