package com.example.wellthydemoapp.view.prodlist

import android.os.Bundle
import com.example.wellthydemoapp.base.BaseActivity

class ProductListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFragment(ProductListFragment())
    }
}
