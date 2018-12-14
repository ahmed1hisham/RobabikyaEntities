package com.greeninnovators.robabikya.models

data class Bin(val points: Int,
               val products: Map<String, Product>) {
    constructor() : this(0, emptyMap())
}