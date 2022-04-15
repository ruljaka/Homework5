package com.ruslangrigoriev.homework5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ruslangrigoriev.homework5.databinding.FragmentContactsListBinding

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {

    private var _binding: FragmentContactsListBinding? = null
    private val binding get() = _binding!!
    private var contract: FragmentContract? = null
    private lateinit var contactsList: List<Contact>
    private lateinit var adapter: ArrayAdapter<Contact>

    companion object {
        const val FRAGMENT_LIST_TAG = "FRAGMENT_LIST_TAG"
        private const val LIST_ARG = "LIST_ARG"
        fun newInstance() = ContactsListFragment()

        @JvmStatic
        fun newInstance(contact: Contact) =
            ContactsListFragment().apply {
                arguments = bundleOf(LIST_ARG to contact)
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentContract) {
            contract = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactsListBinding.bind(view)
        initList()
        initAdapter()
        setupUI()
    }

    private fun initList() {
        contactsList = contract?.getContactsService()?.contacts ?: emptyList()
        arguments?.let { bundle ->
            val editedContact = bundle.getSerializable(LIST_ARG) as Contact
            contactsList.filter { it.id == editedContact.id }
                .map {
                    if (it.firstName != editedContact.firstName) it.firstName = editedContact.firstName
                    if (it.lastName != editedContact.lastName) it.lastName = editedContact.lastName
                    if (it.number != editedContact.number) it.number = editedContact.number
                }
        }
    }

    private fun initAdapter() {
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            contactsList
        )
    }

    private fun setupUI() {
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = adapter.getItem(position)!!
            contract?.fromListToDetails(selectedContact)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}