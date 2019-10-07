package com.example.wellthydemoapp.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    private var mToolBar: Toolbar? = null
    val PRODUCT_ID = "PRODUCT_ID"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellthydemoapp.R.layout.activity_base)
    }

    open fun setUpFragment(fragment: Fragment, productId: String? = "") {
        val args = Bundle()
        args.putString(PRODUCT_ID, productId)
        fragment.setArguments(args)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(com.example.wellthydemoapp.R.id.base_fragment, fragment)
        transaction.commit()
    }

    open fun setUpToolbar(title : String) {
        mToolBar = findViewById(com.example.wellthydemoapp.R.id.include);
        setSupportActionBar(mToolBar)
        mToolBar!!.title = title;
    }

    open fun displayBackButton(shouldDisplayBackButton : Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(shouldDisplayBackButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
