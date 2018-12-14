package com.greeninnovators.robabikya.models

data class Product(val id: String,
                   val name: String,
                   var count: Int,
                   val imageUrl: String,
                   val description: String) {
    constructor() : this("", "", 0, "", "")

}
