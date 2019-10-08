package com.example.wellthydemoapp.view.comment

import android.os.Bundle
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.base.BaseActivity

class CommentsListActivity : BaseActivity() {

    lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = getIntent().getStringExtra(PRODUCT_ID);
        setUpFragment(CommentListFragment(), productId)
        setUpToolbar(getString(R.string.comments))
        displayBackButton(true)

    }
}