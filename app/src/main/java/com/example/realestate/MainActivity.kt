package com.example.realestate

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.realestate.databinding.ActivityMainBinding
import com.example.realestate.fragments.AddFragment
import com.example.realestate.fragments.CartFragment
import com.example.realestate.fragments.HomeFragment
import com.example.realestate.fragments.ItemDetailsFragment
import com.example.realestate.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemDetailsFragment = ItemDetailsFragment()
    fun setToolbarVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.toolbarTitleTv.visibility = View.VISIBLE
        } else {
            binding.toolbarTitleTv.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) //auto binds code with xml file named "activity_main.xml", filename is permanent, if we change they wont recognize and code wont work
        setContentView(binding.root) //only main activity has the setContentView method for binding.


        binding.bottonNavigationView.setOnItemSelectedListener{menuItem->
            val itemId = menuItem.itemId

            when(itemId){
                R.id.item_home-> {

                    showHomeFragment()
                }
                R.id.item_add ->{
                    showAddFragment()


                }
                R.id.item_cart->{
                    showCartFragment()


                }
                R.id.item_person ->{
                    showProfileFragment()


                }
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
    private fun showAddFragment(){
        binding.toolbarTitleTv.text = "Add"
        val addFragment = AddFragment() //instance of AddFragment class.
        val fragmentTransaction = supportFragmentManager.beginTransaction() //init fragment transaction
        fragmentTransaction.replace(binding.fragmentsFl.id, addFragment, "Add") //replace frame layout element with addFragemnt
        fragmentTransaction.commit()
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
