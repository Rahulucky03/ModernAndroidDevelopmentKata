package com.taurus.modernandroiddevelopmentkata.main

import android.widget.FrameLayout
import androidx.core.view.children
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.taurus.modernandroiddevelopmentkata.R
import com.taurus.modernandroiddevelopmentkata.core.di.scope.ActivityScope
import com.taurus.modernandroiddevelopmentkata.navigation.NavigationManager
import kotlinx.android.synthetic.main.activity_main_single_container.*
import javax.inject.Inject

@ActivityScope
class SingleHostTabContainer @Inject constructor(
    private val activity: FragmentActivity,
    private val bottomNavigationViewHolder: BottomNavigationViewHolder
) : NavigationManager.TabContainer {

    // this relies on the layout from activity_main_single_container.xml
    private val tabContainer: FrameLayout by lazy { activity.tabContentContainer }

    override fun bind() {
        // no-op
    }

    override fun getNavController(tabId: Int): NavController =
    // note that tabId is ignored - we always return currently attached NavController
        tabContainer.children.map { it.findNavController() }.first()

    override fun showTab(tabId: Int) {

        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()

        activity.supportFragmentManager.findFragmentById(R.id.tabContentContainer)?.let { fragmentTransaction.detach(it) }

        val tag = tabId.toString()
        var fragment = activity.supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = NavHostFragment()
            fragmentTransaction.add(R.id.tabContentContainer, fragment, tag)
                // NavController is not available before onCreate()
                .runOnCommit { fragment.findNavController().initWith(R.navigation.navigation_graph, tabId) }
        } else {
            fragmentTransaction.attach(fragment)
        }

        fragmentTransaction.commit()

        bottomNavigationViewHolder.highlightTabItem(tabId)
    }
}
