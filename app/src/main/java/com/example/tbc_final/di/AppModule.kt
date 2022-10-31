package com.example.tbc_final.di


import com.example.tbc_final.data.remote.okhttp.HttpClient
import com.example.tbc_final.data.remote.service.BodyPartApiInterface
import com.example.tbc_final.data.remote.service.NutritionApiInterface
import com.example.tbc_final.data.remote.service.StoreApi
import com.example.tbc_final.data.repository.remote.StoreRepositoryImpl
import com.example.tbc_final.domain.repository.remote.StoreRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
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
    fun provideApi(retrofit: Retrofit): BodyPartApiInterface = retrofit.newBuilder().client(HttpClient.okHttpClient).build().create(BodyPartApiInterface::class.java)


    @Provides
    @Singleton
    fun provideStoreApi(retrofit: Retrofit): StoreApi {
        return retrofit.newBuilder().baseUrl("https://run.mocky.io/v3/").build().create(StoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNutritionApi(retrofit: Retrofit):NutritionApiInterface {
        return retrofit.newBuilder().baseUrl("https://api.calorieninjas.com").build().create(NutritionApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreRepository(storeApi: StoreApi): StoreRepository {
        return StoreRepositoryImpl(storeApi)
    }
}