package com.ruslangrigoriev.homework5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_frame_layout,
                    ContactsListFragment.newInstance(),
                    ContactsListFragment.FRAGMENT_LIST_TAG)
                .commit()
        }
    }

    override fun getContactsService() =
        (applicationContext as App).contactService

    override fun fromListToDetails(contact: Contact) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_frame_layout,
                ContactDetailsFragment.newInstance(contact),
                ContactDetailsFragment.FRAGMENT_DETAILS_TAG)
            .addToBackStack(ContactDetailsFragment.FRAGMENT_DETAILS_TAG)
            .commit()

    }

    override fun fromDetailsToList(contact: Contact) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_frame_layout,
                ContactsListFragment.newInstance(contact),
                ContactsListFragment.FRAGMENT_LIST_TAG)
            .commit()
    }

}