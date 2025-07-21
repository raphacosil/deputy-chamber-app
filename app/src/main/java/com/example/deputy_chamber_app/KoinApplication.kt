package com.example.deputy_chamber_app

import com.example.deputy_chamber_app.presentation.viewmodel.DeputyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val networkModule = module {
//    single<Service> {
//        Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(Service::class.java)
//    }
//}
//
//val dataModule = module {
//    factory<AppRepository> {
//        AppRepositoryImpl(get())
//    }
//}
//
//val domainModule = module {
//    factory {
//        GetFeedListUseCase(get())
//    }
//}

val presentationModule = module {
    viewModel {
        DeputyListViewModel()
    }
}