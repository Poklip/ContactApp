package com.example.contacts.data

import com.example.contacts.Contact
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.deleteFromRealm
import java.util.UUID

class ContactRepositoryImpl(private val realm: Realm) : ContactRepository {
    override fun addContact(name: String, surname: String, number: String) {
        realm.executeTransaction {
            val model = it.createObject<Contact>(UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }

            it.insertOrUpdate(model)
        }
    }

    override fun getContacts(): List<Contact> {
        return realm.where(Contact::class.java).findAll()
    }

    override fun editContact(id: String, name: String, surname: String, number: String) {
        realm.executeTransaction {
            getContact(id)?.name = name
            getContact(id)?.surname = surname
            getContact(id)?.number = number
        }
    }

    override fun deleteContact(id: String) {
        realm.executeTransaction {
            getContact(id)?.deleteFromRealm()
        }
    }

    override fun getContact(id: String) : Contact? {
        return realm.where(Contact::class.java).equalTo("id", id).findFirst()
    }

    override fun deleteAllContacts() {
        realm.executeTransaction {
            it.deleteAll()
        }
    }

}