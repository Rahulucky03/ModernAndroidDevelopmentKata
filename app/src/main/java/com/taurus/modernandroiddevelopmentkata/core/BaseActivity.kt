package com.taurus.modernandroiddevelopmentkata.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<VM : ViewModel> : DaggerAppCompatActivity() {

  abstract fun obtainViewModel(): Class<VM>

  protected lateinit var viewModel: VM

  @LayoutRes
  abstract fun layoutResId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResId())
    viewModel = ViewModelProviders.of(this).get(obtainViewModel())
  }

}