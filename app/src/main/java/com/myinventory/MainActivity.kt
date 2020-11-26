package com.myinventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import androidx.camera.core.*
import java.io.FileReader
import java.io.Serializable
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    val REQ_CODE_ADD = 1
    var productList: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


  //      val file = File(applicationContext.filesDir, )


        val viewInv: Button = findViewById(R.id.goToAddProduct)
        viewInv.setOnClickListener {
            val intent = Intent(this, AddProductActivity()::class.java)
            startActivityForResult(intent, REQ_CODE_ADD)
        }

        val viewList: Button = findViewById(R.id.goToLists)
        viewList.setOnClickListener {
            if (productList.isEmpty()) {
                val view = findViewById<View>(R.id.context_view)
                Snackbar.make(view, "You have no items in your inventory. Click  the 'Add to Inventory' to add one.", Snackbar.LENGTH_SHORT)
                        .show()
            }else{
                
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Activity.RESULT_OK){
            val product = intent.extras?.get("product") as Product
            productList.add(product)
        }
    }

    fun loadList(){ // Must be tested. Not sure if loading is correct.
        val file = applicationContext.openFileInput("InventoryList").toString()
        val jsonList = Json.decodeFromString<MutableList<Product>>(file)
        productList.addAll(jsonList)
    }

    fun saveList() {
        val jsonList = Json.encodeToString(productList)
        val file = File(applicationContext.filesDir, "InventoryList")
        file.writeText(jsonList)
    }


}