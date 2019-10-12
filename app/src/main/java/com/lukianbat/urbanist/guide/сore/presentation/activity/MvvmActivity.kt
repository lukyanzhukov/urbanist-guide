package com.lukianbat.urbanist.guide.сore.presentation.activity


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class MvvmActivity<DB : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {

    protected lateinit var binding: DB

    protected abstract val viewModel: VM

    protected abstract val layoutId: Int

    protected abstract val viewModelVariableId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(viewModelVariableId, viewModel)
    }
}