package com.lukianbat.urbanist.guide.сore.dagger.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
}