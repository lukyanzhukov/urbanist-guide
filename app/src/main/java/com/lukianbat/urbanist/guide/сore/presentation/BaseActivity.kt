package com.lukianbat.urbanist.guide.сore.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<DB : ViewDataBinding> : DaggerAppCompatActivity() {

    protected abstract val layoutId: Int

    protected abstract fun initBinding()

    protected abstract fun initViewModel(state: Bundle?)

    var binding: DB? = null

    fun requireBinding(): DB = requireNotNull(binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding?.lifecycleOwner = this

        initViewModel(savedInstanceState)
        initBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
