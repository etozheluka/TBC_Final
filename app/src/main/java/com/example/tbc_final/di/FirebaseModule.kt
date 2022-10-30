package com.example.tbc_final.di

import com.example.tbc_final.data.repository.remote.LoginRepositoryImpl
import com.example.tbc_final.data.repository.remote.SignUpRepositoryImpl
import com.example.tbc_final.domain.repository.remote.LoginRepository
import com.example.tbc_final.domain.repository.remote.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun providesLogInRepository(impl: LoginRepositoryImpl): LoginRepository = impl

    @Provides
    @Singleton
    fun providesSignUpRepository(impl: SignUpRepositoryImpl): SignUpRepository = impl

}