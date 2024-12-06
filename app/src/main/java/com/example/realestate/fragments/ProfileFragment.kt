package com.example.bounty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.realestate.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Setup click listeners for each option
        setupClickListeners(view)

        return view
    }

    private fun setupClickListeners(view: View) {
        view.findViewById<View>(R.id.account_option).setOnClickListener {
            Toast.makeText(requireContext(), "Account Clicked", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.payments_option).setOnClickListener {
            Toast.makeText(requireContext(), "Payments Clicked", Toast.LENGTH_SHORT).show()
        }

        // Add similar click listeners for other options (Notifications, FAQ, etc.)
    }
}
