package com.example.wellthydemoapp.view.comment

import android.os.Bundle
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.base.BaseActivity
import com.example.wellthydemoapp.constant.Constants.Companion.PRODUCT_ID

class CommentsListActivity : BaseActivity() {

    lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = getIntent().getStringExtra(PRODUCT_ID);
        setUpFragmentWithArgs(CommentListFragment(), productId)
        setUpToolbar(getString(R.string.comments))
        displayBackButton(true)

    }
}