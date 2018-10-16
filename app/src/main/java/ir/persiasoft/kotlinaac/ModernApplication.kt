package ir.persiasoft.kotlinaac

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ModernApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}