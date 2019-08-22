package com.lukianbat.urbanist.guide.сore

import com.lukianbat.urbanist.guide.сore.dagger.DaggerApplicationComponent
import com.lukianbat.urbanist.guide.сore.utils.checkInternet
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication() {

    companion object {
        lateinit var instance: App
        fun hasNetwork(): Boolean {
            return instance.checkInternet()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .context(this)
            .create(this)

}