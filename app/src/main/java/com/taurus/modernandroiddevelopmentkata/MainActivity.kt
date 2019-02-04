package com.taurus.modernandroiddevelopmentkata

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.taurus.modernandroiddevelopmentkata.core.BaseActivity
import com.taurus.modernandroiddevelopmentkata.core.BaseFragment
import com.taurus.modernandroiddevelopmentkata.core.extensions.visibility
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationHelper
import com.taurus.modernandroiddevelopmentkata.core.navigation.TabHistory
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), BaseFragment.FragmentListener {

  @Inject
  lateinit var navigationHelper: NavigationHelper

  private val movieNavController: NavController by lazy {
    findNavController(R.id.movieTab)
  }
  private val tvSeriesNavController: NavController by lazy {
    findNavController(R.id.tvSeriesTab)
  }
  private val favouritesNavController: NavController by lazy {
    findNavController(R.id.favouritesTab)
  }
  private val profileNavController: NavController by lazy {
    findNavController(R.id.profileTab)
  }

  override fun obtainViewModel() = MainViewModel::class.java

  override fun layoutResId() = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    navigationHelper.bind(
        this,
        viewModel,
        savedInstanceState,
        movieNavController,
        tvSeriesNavController,
        favouritesNavController,
        profileNavController
    )
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    outState?.putSerializable(TAB_HISTORY, navigationHelper.tabHistory)
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    savedInstanceState?.let {
      navigationHelper.tabHistory = it.getSerializable(TAB_HISTORY) as TabHistory
      navigationHelper.switchTab(bottomNavigationView.selectedItemId, false)
    }
  }

  override fun onBackPressed() {
    navigationHelper.onBackPressed()
  }

  override fun handleBottomBarVisibility(isVisible: Boolean) {
    bottomNavigationView.visibility(isVisible)
  }

  private companion object {
    const val TAB_HISTORY = "tab_history"
  }

}
