package com.example.contacts

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.UUID

@RealmClass
open class Contact : RealmModel {

    @PrimaryKey
    var id: String = ""

    @Required
    var name: String? = ""

    @Required
    var surname: String? = ""

    @Required
    var phoneNumber: String? = ""
}