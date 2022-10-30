package com.example.tbc_final.di


import android.content.Context
import com.example.tbc_final.data.remote.okhttp.HttpClient
import com.example.tbc_final.data.remote.service.BodyPartApiInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .client(HttpClient.okHttpClient)
        .baseUrl("https://exercisedb.p.rapidapi.com/exercises/bodyPart/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): BodyPartApiInterface = retrofit.create(BodyPartApiInterface::class.java)

}