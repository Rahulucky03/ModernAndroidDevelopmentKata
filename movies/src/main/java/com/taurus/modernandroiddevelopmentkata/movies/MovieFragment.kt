package com.taurus.modernandroiddevelopmentkata.movies

import android.util.Log
import android.view.View
import com.taurus.modernandroiddevelopmentkata.core.BaseFragment
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationRouter
import com.taurus.modernandroiddevelopmentkata.core.toolbar.FragmentToolbar
import com.taurus.modernandroiddevelopmentkata.movies.navigation.NavigateFromMoviesToDetails
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment<MovieStateMachine>() {

    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun obtainViewModel() = MovieStateMachine::class.java

    override fun layoutResId() = R.layout.fragment_movie

    override fun toolbarBuilder(): FragmentToolbar {
        return FragmentToolbar.decorateToolbar(R.id.toolbar) {
            withTitle(R.string.movies)
        }
    }

    override fun onReadyToRender(view: View, stateMachine: MovieStateMachine) {
        showDetailButton.setOnClickListener {
            navigationRouter.navigate(NavigateFromMoviesToDetails("From Movies Fragment"))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Movie", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Movie", "onPause")
    }
}
