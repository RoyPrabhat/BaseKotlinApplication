package com.example.wellthydemoapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.view.prodlist.ProductListFragment

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    open fun setUpFragment(fragment: Fragment) {
        val productListFragment = ProductListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.base_fragment, productListFragment)
        transaction.commit()
    }
}
