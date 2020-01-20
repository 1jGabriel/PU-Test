package br.com.base.common.di.modules

import br.com.base.data.Repository
import br.com.base.data.RepositoryImpl
import br.com.base.data.Service
import br.com.base.ui.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide repository
    single { provideRepository(get()) }

    // Provide view model
    viewModel { provideMainViewModel(get()) }
}

fun provideMainViewModel(repository: Repository) =
    MainViewModel(repository)

fun provideRepository(service: Service): Repository =
    RepositoryImpl(service)
