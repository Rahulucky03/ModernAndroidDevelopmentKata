package com.taurus.modernandroiddevelopmentkata.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taurus.modernandroiddevelopmentkata.R
import com.taurus.modernandroiddevelopmentkata.core.navigation.Event
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationCommand
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationRouter
import javax.inject.Inject

class NavigationViewModel @Inject constructor() : ViewModel(), NavigationRouter {

    private val _navigationRouter = MutableLiveData<Event<NavigationCommand>>()

    val navigationRouter: LiveData<Event<NavigationCommand>>
        get() = _navigationRouter

    val tabHistory: TabHistory = TabHistory(R.id.navigation_movies)

    override fun navigate(navigationCommand: NavigationCommand) {
        _navigationRouter.postValue(Event(navigationCommand))
    }
}
