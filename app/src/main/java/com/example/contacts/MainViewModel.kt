package com.example.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.delete
import io.realm.kotlin.deleteFromRealm
import io.realm.kotlin.where
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

    fun editContact(id: String, name: String, surname: String, phoneNumber: String) {
        realm.executeTransaction {
            getContact(id)?.name = name
            getContact(id)?.surname = surname
            getContact(id)?.phoneNumber = phoneNumber
        }
    }

    fun deleteContact(id: String) {
        realm.executeTransaction {
            getContact(id)?.deleteFromRealm()
        }
    }

    fun getContact(id: String) : Contact? {
        return realm.where(Contact::class.java).equalTo("id", id).findFirst()
    }

    fun deleteAllContacts() {
        realm.executeTransaction {
            it.delete<Contact>()
        }
    }


    private fun getAllContacts(): MutableLiveData<List<Contact>> {
        val list = MutableLiveData<List<Contact>>()
        val allContacts = realm.where(Contact::class.java).findAll()
        list.value = allContacts.subList(0, allContacts.size)
        return list
    }
}