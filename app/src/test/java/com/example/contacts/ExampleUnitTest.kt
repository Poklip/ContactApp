package com.example.contacts

import android.app.Instrumentation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testViewModel_isAdded() {
        val repository = ContactRepository()
        val contact = DummyContact(
            name = "Kova",
            surname = "Vedrov",
            number = "+79854562777"
        )

        repository.addContact(contact)

        val list = repository.getAllContacts()
        val lastContact = list.last()

        assertEquals(contact, lastContact)


    }
}