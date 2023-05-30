package com.example.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactRepository
import io.realm.kotlin.delete
import io.realm.kotlin.deleteFromRealm

class MainViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    val allContacts: ContactLiveData
        get() = getAllContacts() as ContactLiveData

    fun addContact(name: String, surname: String, number: String) {
        contactRepository.addContact(name = name, surname = surname, number = number)
    }

    fun editContact(id: String, name: String, surname: String, number: String) {
        contactRepository.editContact(id = id, name = name, surname = surname, number = number)
    }

    fun deleteContact(id: String) {
        contactRepository.deleteContact(id = id)
    }

    fun getContact(id: String) : Contact? {
        return contactRepository.getContact(id = id)
    }

    fun deleteAllContacts() {
        contactRepository.deleteAllContacts()
    }


    private fun getAllContacts(): MutableLiveData<List<Contact>> {
        val list = ContactLiveData()
        val allContacts = contactRepository.getContacts()
        list.value = allContacts.subList(0, allContacts.size)
        return list
    }
}