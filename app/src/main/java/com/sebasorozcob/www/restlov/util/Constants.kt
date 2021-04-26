package com.sebasorozcob.www.restlov.util

class Constants {

    companion object {

        // PERMISSIONS
        const val CODE_GPS = 0

        // URL
        const val BASE_URL = "https://restlov.herokuapp.com"

        // Bundles
        const val RESTAURANT_RESULT_KEY = "restaurantBundle"

        // Online - Offline
        const val PREFERENCES_BACK_ONLINE = "backOnline"
        const val PREFERENCES_LONGITUDE = "longitude"
        const val PREFERENCES_LATITUDE = "latitude"

        // API QUERIES KEYS
        const val QUERIES_LONGITUDE_LATITUDE = "longitude"
        const val QUERIES_LATITUDE = "latitude"

        // Preferences
        const val PREFERENCES_NAME = "restlov preferences"

        // Room database
        const val DATABASE_NAME = "restlov_database"
        const val RESTAURANTS_TABLE = "restaurants_table"
    }

    enum class Currency {
        Dollar, COP
    }

    enum class ChallengeType {
        PRICE, SALES
    }
}