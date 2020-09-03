package com.kluivert.kwota.presentation.di.remoteDataModule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kluivert.kwota.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun ProvideGsonBuilder() : Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun ProvideApi(gson : Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))


    }

}