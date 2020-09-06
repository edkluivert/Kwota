package com.kluivert.kwota.di.repositoryModule

import androidx.transition.Transition
import com.kluivert.kwota.data.network.api.QuoteApi
import com.kluivert.kwota.data.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {


    @Provides
    fun providesDataRepo(quoteApi: QuoteApi):DataRepository{
        return DataRepository(quoteApi)
    }

}