package com.greeninnovators.robabikya.models

data class User (val uid: String,
                 val name: String,
                 val username: String,
                 val phoneNumber: String,
                 val profileImageUrl: String,
                 val credit: Int,
                 val bin: Bin,
                 val requests: Map<String, PickupRequest>
                 ) {
    constructor() : this("", "", "", "", "", 0, Bin(0, emptyMap()), emptyMap())
}