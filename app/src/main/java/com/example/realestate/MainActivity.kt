package com.example.realestate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.realestate.databinding.ActivityMainBinding
import com.example.realestate.fragments.AddFragment
import com.example.realestate.fragments.CartFragment
import com.example.realestate.fragments.HomeFragment
import com.example.realestate.fragments.ItemDetailsFragment
import com.example.realestate.fragments.ProfileFragment

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding
    private val itemDetailsFragment = ItemDetailsFragment()
    private val addFragment = AddFragment() //instance of AddFragment class.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the default fragment on startup
        if (savedInstanceState == null) { // Ensure this only happens on the first launch
            showHomeFragment()
        }

        binding.bottonNavigationView.setOnItemSelectedListener { menuItem ->
            val itemId = menuItem.itemId
            when (itemId) {
                R.id.item_home -> showHomeFragment()
                R.id.item_add -> showAddFragment("Fruits")
                R.id.item_cart -> showCartFragment()
                R.id.item_person -> showProfileFragment()
            }
            true
        }
    }


    private fun showHomeFragment(){
        binding.toolbarTitleTv.text = "Home"
        val homeFragment = HomeFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentsFl.id, homeFragment, "Home")
        fragmentTransaction.commit()
    }

    override fun showAddFragment(category: String) {
        binding.toolbarTitleTv.text = "Add"

        val addFragment = AddFragment()
        val bundle = Bundle().apply {
            putString("category", category)
        }
        addFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentsFl.id, addFragment, "Add")
            .addToBackStack("AddFragment")
            .commit()
    }

    private fun showCartFragment(){
        binding.toolbarTitleTv.text = "Cart"
        val cartFragment = CartFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentsFl.id, cartFragment, "Cart")
        fragmentTransaction.commit()
    }

    private fun showProfileFragment(){
        binding.toolbarTitleTv.text = "Profile"
        val profileFragment = ProfileFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentsFl.id, profileFragment, "Profile")
        fragmentTransaction.commit()
    }


}
