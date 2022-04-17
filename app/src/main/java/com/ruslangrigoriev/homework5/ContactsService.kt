package com.ruslangrigoriev.homework5

import com.github.javafaker.Faker

class ContactsService {

    private val faker = Faker.instance()
    val contacts: List<Contact> = (1..10).map {
        Contact(
            id = it,
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            number = faker.phoneNumber().cellPhone()
        )
    }
}