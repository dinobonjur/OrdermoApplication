package com.example.ordermoapplication.utils

object Constants {
    // SharedPreferences Keys
    const val PREF_NAME = "OrderMorphPref"
    const val KEY_USER = "user"
    const val KEY_IS_LOGGED_IN = "isLoggedIn"
    const val KEY_REMEMBER_ME = "rememberMe"

    // Intent Extras
    const val EXTRA_USER = "extra_user"
    const val EXTRA_USER_ID = "extra_user_id"

    // API Endpoints (for future use)
    const val BASE_URL = "https://api.ordermorph.com/"
    const val LOGIN_ENDPOINT = "auth/login"
    const val REGISTER_ENDPOINT = "auth/register"

    // Validation Rules
    const val MIN_PASSWORD_LENGTH = 6
    const val MAX_PASSWORD_LENGTH = 20
    const val MIN_NAME_LENGTH = 2
    const val MAX_NAME_LENGTH = 50
    const val PHONE_LENGTH = 10

    // Default Values
    const val DEFAULT_USER_IMAGE = "ic_person"
    const val DEFAULT_CURRENCY = "₱"
}