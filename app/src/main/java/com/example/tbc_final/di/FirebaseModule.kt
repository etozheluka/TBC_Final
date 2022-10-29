package com.example.tbc_final.di

import com.example.tbc_final.data.repository.LoginRepositoryImpl
import com.example.tbc_final.data.repository.SignUpRepositoryImpl
import com.example.tbc_final.domain.repository.LoginRepository
import com.example.tbc_final.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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