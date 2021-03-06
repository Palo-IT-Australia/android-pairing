package com.paloit.coin.di

import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.repository.PricingRepository
import com.paloit.coin.platform.repository.PricingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PricingModule {
    @Singleton
    @Provides
    fun providePricingRepository(bpiApi: BpiApi): PricingRepository {
        return PricingRepositoryImpl(bpiApi)
    }
}
