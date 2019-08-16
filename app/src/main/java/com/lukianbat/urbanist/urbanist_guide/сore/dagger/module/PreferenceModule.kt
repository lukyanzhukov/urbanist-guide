package com.lukianbat.urbanist.urbanist_guide.сore.dagger.module

import android.content.Context
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.PreferenceRepository
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.PreferenceRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    fun providePreferenceRepo(context: Context): PreferenceRepository =
        PreferenceRepositoryImpl(context)
}