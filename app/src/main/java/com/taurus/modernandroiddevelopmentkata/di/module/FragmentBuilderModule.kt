package com.taurus.modernandroiddevelopmentkata.di.module

import com.taurus.modernandroiddevelopmentkata.core.di.scope.FragmentScope
import com.taurus.modernandroiddevelopmentkata.detail.DetailBuilderModule
import com.taurus.modernandroiddevelopmentkata.favourites.FavouriteFragment
import com.taurus.modernandroiddevelopmentkata.favourites.FavouriteModule
import com.taurus.modernandroiddevelopmentkata.movies.MovieFragment
import com.taurus.modernandroiddevelopmentkata.movies.MovieModule
import com.taurus.modernandroiddevelopmentkata.profile.ProfileFragment
import com.taurus.modernandroiddevelopmentkata.profile.ProfileModule
import com.taurus.modernandroiddevelopmentkata.similarmovies.SimilarMoviesModule
import com.taurus.modernandroiddevelopmentkata.tvseries.TVSeriesFragment
import com.taurus.modernandroiddevelopmentkata.tvseries.TVSeriesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    DetailBuilderModule::class,
    SimilarMoviesModule::class
])
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun movieFragment(): MovieFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TVSeriesModule::class])
    abstract fun tvSeriesFragment(): TVSeriesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavouriteModule::class])
    abstract fun favouriteFragment(): FavouriteFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun profileFragment(): ProfileFragment

}
