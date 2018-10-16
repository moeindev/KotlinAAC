package ir.persiasoft.kotlinaac

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ir.persiasoft.kotlinaac.ViewModels.MainViewModel
import ir.persiasoft.kotlinaac.di.ViewModelKey

//injector!
@Module
internal abstract class MainActivityModule{

//    @Module
//
//    companion object {
//
//        @JvmStatic
//
//        @Provides
//
//        internal fun providesMainViewModelFactory(repositoryModel: RepositoryModel)
//                : MainViewModelFactory {
//            return MainViewModelFactory(repositoryModel)
//
//        }
//
//    }

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}