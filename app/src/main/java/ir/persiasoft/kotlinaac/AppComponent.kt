package ir.persiasoft.kotlinaac

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ir.persiasoft.kotlinaac.di.ViewModelBuilder
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelBuilder::class,
            MainActivityModule::class])

interface AppComponent : AndroidInjector<ModernApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ModernApplication>()
}