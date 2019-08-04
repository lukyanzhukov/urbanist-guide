package com.lukianbat.urbanist.urbanist_guide.сore.dagger.module

import android.content.Context
import com.lukianbat.urbanist.urbanist_guide.сore.domain.PreferenceRepository
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    fun providePreferenceRepo(context: Context): PreferenceRepository = PreferenceRepository(context)
}