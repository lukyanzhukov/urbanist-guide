package com.lukianbat.urbanist.guide.сore.dagger.module.feature

import com.lukianbat.urbanist.guide.feature.list.domain.usecases.dagger.PlacesListUseCaseModule
import com.lukianbat.urbanist.guide.feature.list.presentation.PlacesListActivity
import com.lukianbat.urbanist.guide.feature.list.presentation.dagger.PlacesListActivityModule
import com.lukianbat.urbanist.guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PlacesListUseCaseModule::class])
interface PlacesListModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [PlacesListActivityModule::class])
    fun PlacesListActivityInjector(): PlacesListActivity

}