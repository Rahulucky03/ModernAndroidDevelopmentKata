package com.taurus.modernandroiddevelopmentkata.navigation.executor

import com.taurus.modernandroiddevelopmentkata.core.di.scope.ActivityScope
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationRouter
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module
class NavigationExecutorModule {

    @Provides
    @ActivityScope
    @ElementsIntoSet
    fun provideRouters(
        appNavigationExecutor: AppNavigationExecutor,
        moviesNavigationExecutor: MoviesNavigationExecutor
    ): Set<NavigationRouterFacade.NavigationExecutor> =
        setOf(appNavigationExecutor, moviesNavigationExecutor)

    @Provides
    @ActivityScope
    fun provideNavigationRouter(navigationRouterFacade: NavigationRouterFacade):
        NavigationRouter = navigationRouterFacade
}
