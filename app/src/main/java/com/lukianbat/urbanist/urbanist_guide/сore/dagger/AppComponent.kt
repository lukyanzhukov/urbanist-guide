package com.lukianbat.urbanist.urbanist_guide.сore.dagger

import android.content.Context
import com.lukianbat.urbanist.urbanist_guide.сore.App
import com.lukianbat.urbanist.urbanist_guide.сore.dagger.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        @BindsInstance
        abstract fun context(context: Context): Builder
    }
}
