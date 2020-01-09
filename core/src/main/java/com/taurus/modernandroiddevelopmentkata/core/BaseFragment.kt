package com.taurus.modernandroiddevelopmentkata.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.taurus.modernandroiddevelopmentkata.core.di.viewmodel.ViewModelFactory
import com.taurus.modernandroiddevelopmentkata.core.toolbar.FragmentToolbar
import com.taurus.modernandroiddevelopmentkata.core.toolbar.ToolbarManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

    interface FragmentListener {
        fun handleBottomBarVisibility(isVisible: Boolean)
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<VM>

    /**
     * ViewModel instance that provided by ViewModelProvider
     */
    private lateinit var stateMachine: VM

    private var fragmentListener: FragmentListener? = null

    abstract fun obtainViewModel(): Class<VM>

    @LayoutRes
    abstract fun layoutResId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        try {
            this.fragmentListener = context as FragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement FragmentListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateMachine = ViewModelProviders.of(this, viewModelFactory).get(obtainViewModel())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) =
        inflater.inflate(layoutResId(), container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ToolbarManager(toolbarBuilder(), view, findNavController()).prepareToolbar()
        fragmentListener?.handleBottomBarVisibility(isBottomBarEnabled)
        onReadyToRender(view, stateMachine)
    }

    /**
     * Override and return false if you don't need the bottom bar.
     */
    protected open val isBottomBarEnabled = true


    protected abstract fun toolbarBuilder(): FragmentToolbar

    protected abstract fun onReadyToRender(view: View, stateMachine: VM)

}
