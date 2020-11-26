package com.myinventory

import android.net.Uri
import android.widget.ImageView
import java.io.Serializable


class Product(name: String = "", owner: String = "", year: Int = 0, imageuri: String = "") : Serializable {

    var name: String = name
    var owner: String = owner
    var year: Int = year
    var imageuri: String = imageuri
}