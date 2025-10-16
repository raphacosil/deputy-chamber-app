package com.example.deputy_chamber_app

import com.example.deputy_chamber_app.data.datasource.DeputyPagingSource
import com.example.deputy_chamber_app.data.repository.DeputyRepositoryImpl
import com.example.deputy_chamber_app.data.service.DeputyService
import com.example.deputy_chamber_app.domain.repository.DeputyRepository
import com.example.deputy_chamber_app.domain.usecase.GetDeputiesUseCase
import com.example.deputy_chamber_app.domain.usecase.GetDeputyDetailUseCase
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyDetailViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<DeputyService> {
        Retrofit.Builder()
            .baseUrl("https://dadosabertos.camara.leg.br/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeputyService::class.java)
    }
}

val dataModule = module {
    factory {
        DeputyPagingSource(get())
    }
    factory<DeputyRepository> {
        DeputyRepositoryImpl(get(), get())
    }
}

val domainModule = module {
    factory {
        GetDeputiesUseCase(get())
    }
    factory {
        GetDeputyDetailUseCase(get())
    }
}

val presentationModule = module {
    viewModel {
        DeputyListViewModel(get())
    }
    viewModel {
        DeputyDetailViewModel(get())
    }
}