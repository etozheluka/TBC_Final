package com.example.tbc_final.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object DataStore {
    //TODO 3) // CHANGE NAME
    private const val NAME = "steps_preferences"

    val Context.store by preferencesDataStore(
        name = NAME
    )
}