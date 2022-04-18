package com.ruslangrigoriev.homework5

interface FragmentContract {
    fun getContactsService() : ContactsService
    fun fromListToDetails(contact: Contact)
    fun fromDetailsToList(contact: Contact)
}