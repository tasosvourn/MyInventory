package com.myinventory

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddProductActivity(): AppCompatActivity() {

    val REQ_CODE_ADD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        val product = Product()
        val submition: Button = findViewById(R.id.submit)
        submition.setOnClickListener {
            product.name = R.id.productName.toString()
            product.owner = R.id.owner.toString()
            product.year = R.id.yearPur
            product.imageuri = R.id.prodImage.toString()
            val retPro = Intent().apply{
                    putExtra("product", product::class.java)
                }
            setResult(Activity.RESULT_OK, retPro)
            finish()
        }

        val capture: Button = findViewById(R.id.cameraButton)
        capture.setOnClickListener {
//            dispatchTakePictureIntent()
            val intent = Intent(this, Capture()::class.java)
            startActivityForResult(intent, REQ_CODE_ADD)
        }
    }

    lateinit var currentPhotoPath: String
    val product = Product()


  /*  @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
    val REQUEST_TAKE_PHOTO = 1

    fun dispatchTakePictureIntent() {
               Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.myinventory.android.fileprovider",
                            it
                    )
                    product.imageuri = photoURI.toString()
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                    finish()
                }
            }
        }
    } */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK){
            val iv: ImageView = findViewById(R.id.prodImage)
            iv.setImageURI(Uri.parse(intent.extras?.get("photoDirectory").toString()))
        }
    }
}
