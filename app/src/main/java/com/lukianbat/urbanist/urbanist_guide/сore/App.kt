package com.lukianbat.urbanist.urbanist_guide.сore

import com.lukianbat.urbanist.urbanist_guide.сore.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .context(this)
            .create(this)
}