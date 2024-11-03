package com.example.realestate

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realestate.databinding.ActivityMainBinding
import com.example.realestate.framgments.AddFragment
import com.example.realestate.framgments.CartFragment
import com.example.realestate.framgments.HomeFragment
import com.example.realestate.framgments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
        val addFragment = AddFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentsFl.id, addFragment, "Add")
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
