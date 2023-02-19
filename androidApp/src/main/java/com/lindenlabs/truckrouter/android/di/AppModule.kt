package com.lindenlabs.truckrouter.android.di

import android.app.Application
import android.content.Context
import com.lindenlabs.truckrouter.AndroidPlatform
import com.lindenlabs.truckrouter.ResourceReader
import com.lindenlabs.truckrouter.domain.GetScheduleDomainEntity
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideApp(@ApplicationContext context: Context) = context

    @Provides
    fun provideResourceReader(@ApplicationContext appContext: Context) = ResourceReader(appContext)

    @Provides
    fun provideGetScheduleDomainInteractor(context: Context): GetScheduleDomainEntity {
        return GetScheduleDomainEntity(
            platform = AndroidPlatform(context),
            mapper = ScheduleDomainMapper()
        )
    }
}