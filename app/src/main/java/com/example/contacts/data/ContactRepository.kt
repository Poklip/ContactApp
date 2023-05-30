package com.example.contacts.data

import com.example.contacts.Contact

interface ContactRepository {

    fun addContact(name: String, surname: String, number: String)

    fun getContacts() : List<Contact>

    fun getContact(id: String) : Contact?

    fun deleteContact(id: String)

    fun editContact(id: String, name: String, surname: String, number: String)

    fun deleteAllContacts()
}