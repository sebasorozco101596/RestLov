package com.sebasorozcob.www.restlov.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.sebasorozcob.www.restlov.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.sebasorozcob.www.restlov.util.Constants.Companion.PREFERENCES_LATITUDE
import com.sebasorozcob.www.restlov.util.Constants.Companion.PREFERENCES_LONGITUDE
import com.sebasorozcob.www.restlov.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
        val longitude = doublePreferencesKey(PREFERENCES_LONGITUDE)
        val latitude = doublePreferencesKey(PREFERENCES_LATITUDE)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    suspend fun saveLongitudeLatitude(
        longitude: Double,
        latitude: Double
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.longitude] = longitude
            preferences[PreferenceKeys.latitude] = latitude
        }
    }

    val readLongitudeLatitude: Flow<LongitudeLatitude> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val longitude = preferences[PreferenceKeys.longitude] ?: 0.0
            val latitude = preferences[PreferenceKeys.latitude] ?: 0.0
            LongitudeLatitude(
                longitude,
                latitude
            )
        }

    val readBackOnline: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}

data class LongitudeLatitude(
    val longitude: Double,
    val latitude: Double
)