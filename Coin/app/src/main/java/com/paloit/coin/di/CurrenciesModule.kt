package com.paloit.coin.di

import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.repository.CurrenciesRepository
import com.paloit.coin.platform.repository.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrenciesModule {
    @Singleton
    @Provides
    fun providePricingRepository(bpiApi: BpiApi): CurrenciesRepository {
        return CurrenciesRepositoryImpl(bpiApi)
    }
}
