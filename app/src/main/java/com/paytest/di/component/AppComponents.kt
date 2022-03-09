package com.paytest.di.component

import com.paytest.di.AppModule
import com.paytest.di.NetModule
import com.paytest.di.ViewModelModule
import com.paytest.utils.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ViewModelModule::class])
interface AppComponents {
    fun inject(baseFragment: BaseFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponents
        fun netModule(netModule: NetModule): Builder
        fun appModule(appModule: AppModule): Builder
    }
}