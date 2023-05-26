package com.example.contacts

class ContactRepository {
    private val contactList = mutableListOf<DummyContact>()

    fun addContact(contact: DummyContact) {
        contactList.add(contact)
    }

    fun getAllContacts(): MutableList<DummyContact> {
        return contactList
    }
}

data class DummyContact(
    val name: String,
    val surname: String,
    val number: String
)