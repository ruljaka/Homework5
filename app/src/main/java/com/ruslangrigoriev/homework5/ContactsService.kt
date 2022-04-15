package com.ruslangrigoriev.homework5

import com.github.javafaker.Faker

class ContactsService {

    private val faker = Faker.instance()
    val contacts: List<Contact> = (1..15).map {
        Contact(
            id = faker.idNumber().valid(),
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            number = faker.phoneNumber().cellPhone()
        )
    }
}