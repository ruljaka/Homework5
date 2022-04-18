package com.ruslangrigoriev.homework5

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ruslangrigoriev.homework5.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contract: FragmentContract? = null
    private val contact: Contact
        get() = requireArguments().getSerializable(DETAILS_ARG) as Contact

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentContract) {
            contract = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContactDetailsBinding.bind(view)
        with(binding) {
            firstNameEt.setText(contact.firstName)
            lastNameEt.setText(contact.lastName)
            numberEt.setText(contact.number)
            saveBtn.setOnClickListener {
                contact.firstName = firstNameEt.text.toString()
                contact.lastName = lastNameEt.text.toString()
                contact.number = numberEt.text.toString()
                contract?.fromDetailsToList(contact)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_DETAILS_TAG = "FRAGMENT_DETAILS_TAG"
        private const val DETAILS_ARG = "DETAILS_ARG"

        fun newInstance(contact: Contact) =
            ContactDetailsFragment().apply {
                arguments = bundleOf(DETAILS_ARG to contact)
            }
    }
}