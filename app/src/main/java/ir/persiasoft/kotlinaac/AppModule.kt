package ir.persiasoft.kotlinaac

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule{
    @Provides
    fun providesContext(application: ModernApplication): Context{
        //return main application context
        return application.applicationContext
    }
}



