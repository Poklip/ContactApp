package com.example.contacts.di

import com.example.contacts.MainViewModel
import com.example.contacts.data.ContactRepository
import com.example.contacts.data.ContactRepositoryImpl
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.mongodb.AppConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())

        val configuration = RealmConfiguration.Builder()
            .name("contacts.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(8)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        Realm.getDefaultInstance()
    }

    single<ContactRepository> {
        ContactRepositoryImpl(get<Realm>())
    }

    viewModel {MainViewModel(get<ContactRepository>())}
}