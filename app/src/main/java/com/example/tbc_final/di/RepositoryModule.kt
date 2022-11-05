package com.example.tbc_final.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.tbc_final.data.remote.service.StoreApi
import com.example.tbc_final.data.repository.local.FavoritesRepositoryImpl

import com.example.tbc_final.data.repository.remote.BodyPartRepositoryImpl
import com.example.tbc_final.data.repository.local.StepPreferencesRepositoryImpl
import com.example.tbc_final.data.repository.remote.NutritionRepositoryImpl
import com.example.tbc_final.data.repository.remote.StoreRepositoryImpl
import com.example.tbc_final.domain.repository.local.FavoritesRepository
import com.example.tbc_final.domain.repository.remote.BodyPartRepository
import com.example.tbc_final.domain.repository.local.StepPreferencesRepository
import com.example.tbc_final.domain.repository.remote.NutritionRepository
import com.example.tbc_final.domain.repository.remote.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideStoreRepository(
        storeRepositoryImpl: StoreRepositoryImpl
    ): StoreRepository


    @Singleton
    @Binds
    abstract fun bindStepPreferencesRepository(
        stepPreferencesRepositoryImpl: StepPreferencesRepositoryImpl
    ): StepPreferencesRepository

    @Singleton
    @Binds
    abstract fun bindNutritionRepository(
        nutritionRepositoryImpl: NutritionRepositoryImpl
    ): NutritionRepository

    @Singleton
    @Binds
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository


    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        bodyPartRepositoryImpl: BodyPartRepositoryImpl
    ): BodyPartRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                produceFile = { applicationContext.preferencesDataStoreFile("steps_preferences") }
            )
        }
    }

}


