package com.greeninnovators.robabikya.models

data class PickupRequest(val uid: String,
                         val products: Map<String, Product>,
                         val pickupLocation: String,
                         val dateOfRequest: String,
                         val dateOfPickup: String,
                         val status: String) {
    constructor() : this("", emptyMap<String, Product>(), "", "", "", "")
}
