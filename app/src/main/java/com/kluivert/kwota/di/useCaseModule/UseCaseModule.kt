package com.kluivert.kwota.di.useCaseModule

import com.kluivert.kwota.data.repository.DataRepository
import com.kluivert.kwota.useCase.DataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    fun providesDataUseCase(dataRepository: DataRepository):DataUseCase{
        return DataUseCase(dataRepository)
    }
}