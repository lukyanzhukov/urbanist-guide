package com.lukianbat.urbanist.guide.сore.dagger.module.feature

import com.lukianbat.urbanist.guide.feature.start.domain.usecases.dagger.CityNameUseCaseModule
import com.lukianbat.urbanist.guide.feature.start.presentation.StartActivity
import com.lukianbat.urbanist.guide.feature.start.presentation.dagger.StartActivityModule
import com.lukianbat.urbanist.guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CityNameUseCaseModule::class])
interface StartModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [StartActivityModule::class])
    fun StartActivityInjector(): StartActivity
}