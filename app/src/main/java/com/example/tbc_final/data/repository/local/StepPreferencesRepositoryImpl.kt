package com.example.tbc_final.data.repository.local


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbc_final.data.repository.local.StepPreferencesRepositoryImpl.Companion.KEY_TOTAL_GOAL
import com.example.tbc_final.domain.repository.local.StepPreferencesRepository
import com.example.tbc_final.service.MotionActivityService.Companion.KEY_POINTS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class StepPreferencesRepositoryImpl @Inject constructor(
    private val stepPreferences: DataStore<Preferences>,
    user:FirebaseAuth
): StepPreferencesRepository {


    private val auth = user.currentUser?.email



    override suspend fun putPoints(points: String) {
        Result.runCatching {

            stepPreferences.edit {preferences ->
                preferences[stringPreferencesKey("$auth P")] = points

            }
        }
    }

    override suspend fun putStep(steps: String) {
        Result.runCatching {
            stepPreferences.edit {preferences ->
                preferences[stringPreferencesKey("$auth G")] = steps

            }
        }
    }

    override suspend fun putTotalStep(steps: String) {
        Result.runCatching {
            stepPreferences.edit {preferences ->
                preferences[stringPreferencesKey("$auth TG")] = steps

            }
        }
    }

    override suspend fun getStep(): Result<String> {
        return Result.runCatching {
            val flow = stepPreferences.data.catch {
                if(it is IOException){
                    emit(emptyPreferences())
                }else{
                    throw it
                }
            }
                .map {
                    it[stringPreferencesKey("$auth G")]
                }
            val value = flow.firstOrNull() ?: "0"
            value
        }
    }

    override suspend fun getTotalStep(): Result<String> {
        return Result.runCatching {
            val flow = stepPreferences.data.catch {
                if(it is IOException){
                    emit(emptyPreferences())
                }else{
                    throw it
                }
            }
                .map {
                    it[stringPreferencesKey("$auth TG")]
                }
            val value = flow.firstOrNull() ?: "0"
            value
        }
    }

    override suspend fun getPoints(): Result<String> {
        return Result.runCatching {
            val flow = stepPreferences.data.catch {
                if(it is IOException){
                    emit(emptyPreferences())
                }else{
                    throw it
                }
            }
                .map {
                    it[stringPreferencesKey("$auth P")]
                }
            val value = flow.firstOrNull() ?: "0"
            value
        }
    }

    companion object{

        private val KEY_GOAL = " GOAL"
        private val KEY_TOTAL_GOAL = " TOTAL"
        private val KEY_POINTS = " POINTS"
    }
}