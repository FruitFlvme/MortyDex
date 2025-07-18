package com.fruitflvme.mortydex.app.di

import com.fruitflvme.mortydex.data.di.DatabaseModule
import com.fruitflvme.mortydex.data.di.NetworkModule
import com.fruitflvme.mortydex.data.di.RepositoryModule
import com.fruitflvme.mortydex.domain.di.DomainModule
import com.fruitflvme.mortydex.presentation.di.PresentationModule

val appModules = listOf(
    DatabaseModule.module,
    NetworkModule.module,
    RepositoryModule.module,
    DomainModule.module,
    PresentationModule.module
)