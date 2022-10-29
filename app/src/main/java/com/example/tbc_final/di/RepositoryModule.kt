package com.example.tbc_final.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.tbc_final.data.local.datastore.DataStore.store
import com.example.tbc_final.data.repository.BodyPartRepositoryImpl
import com.example.tbc_final.data.repository.StepPreferencesRepositoryImpl
import com.example.tbc_final.domain.repository.BodyPartRepository
import com.example.tbc_final.domain.repository.StepPreferencesRepository
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

    @Singleton
    @Binds
    abstract fun bindStepPreferencesRepository(
        stepPreferencesRepositoryImpl:StepPreferencesRepositoryImpl
    ): StepPreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.store
        }


    }

    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        bodyPartRepositoryImpl: BodyPartRepositoryImpl
    ): BodyPartRepository
}