package com.example.ordermoapplication.models

import java.io.Serializable

data class User(
    var fullName: String = "",
    var email: String = "",
    var phone: String = "",
    var password: String = ""
) : Serializable