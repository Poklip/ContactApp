package com.example.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.createObject
import java.util.UUID

class MainViewModel : ViewModel() {
    private var realm: Realm = Realm.getDefaultInstance()

    val allContacts: LiveData<List<Contact>> = getAllContacts()

    fun addContact(name: String, surname: String, phoneNumber: String) {
        realm.executeTransaction {
            val model = it.createObject<Contact>(UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.phoneNumber = phoneNumber
            }

            it.insertOrUpdate(model)
        }
    }

    private fun getAllContacts(): MutableLiveData<List<Contact>> {
        val list = MutableLiveData<List<Contact>>()
        val allContacts = realm.where(Contact::class.java).findAll()
        list.value = allContacts.subList(0, allContacts.size)
        return list
    }
}