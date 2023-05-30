package com.example.contacts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.contacts.data.DummyContact

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkAllComponents_isVisible() {
        // Context of the app under test.

        val names = listOf("Kova", "Satan", "Luci", "Bob", "Behemoth")
        val surnames = listOf("Vedrov", "God", "Lord", "Bobert", "Mohhamed")
        val numbers = listOf("+79909909090", "+78818818181", "+77727727272", "+76636636363", "+75545545454")
        val hundred = "Razbakova"
        var contact: DummyContact
        fun getContact(names: List<String>, surnames: List<String>, numbers: List<String>) : DummyContact {
            return DummyContact(name = names.random(), surname = surnames.random(), number = numbers.random())
        }


        fun testCreateContacts(number: Int) {
            for (i in 0..number) {
                contact = getContact(names, surnames, numbers)

                onView(withId(R.id.fabAddContact))
                    .check(matches(isDisplayed()))
                    .perform(click())

                onView(withId(R.id.etName))
                    .check(matches(isDisplayed()))
                    .perform(typeText(contact.name))
                    .check(matches(withText(contact.name)))

                onView(withId(R.id.etSurname))
                    .check(matches(isDisplayed()))
                    .perform(typeText(contact.surname))
                    .check(matches(withText(contact.surname)))

                onView(withId(R.id.etNumber))
                    .check(matches(isDisplayed()))
                    .perform(typeText(contact.number))
                    .check(matches(withText(contact.number)))

                onView(withId(R.id.btnSave))
                    .check(matches(isDisplayed()))
                    .perform(click())

                onView(withId(R.id.rvContactList))
                    .check(matches(isDisplayed()))
            }
        }

        fun testEditAndDeleteContact() {
            onView(withId(R.id.rvContactList))
                .check(matches(isDisplayed()))
                .perform(click())

            onView(withId(R.id.etEditSurname))
                .check(matches(isDisplayed()))
                .perform(typeText(hundred))
                .check(matches(withText(hundred)))

            onView(withId(R.id.btnSaveChanges))
                .check(matches(isDisplayed()))
                .perform(click())

            onView(withId(R.id.rvContactList))
                .check(matches(isDisplayed()))
                .perform(click())

            onView(withId(R.id.btnDeleteContact))
                .check(matches(isDisplayed()))
                .perform(click())
        }

        //mission complete
        testCreateContacts(10)

        //mission complete
        testEditAndDeleteContact()
    }

    @Test //mission complete
    fun deleteAllContacts_isVisible() {
        onView(withId(R.id.fabDeleteAllContacts))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.fabDeleteAllContactsAreYouSure))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.btnDeleteAllContactsSure))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(9000)
    }

    @Test //mission complete
    fun callSomebody_isVisible() {
        onView(withId(R.id.rvContactList))
            .check(matches(isDisplayed()))
            .perform(click())


        onView(withId(R.id.btnCall))
            .check(matches(isDisplayed()))
            .perform(click())
    }
}
