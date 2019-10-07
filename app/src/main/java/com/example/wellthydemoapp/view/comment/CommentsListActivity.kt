package com.example.wellthydemoapp.view.comment

import android.os.Bundle
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.base.BaseActivity
import com.example.wellthydemoapp.view.prodlist.ProductListFragment

class CommentsListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFragment(CommentListFragment())
        setUpToolbar(getString(R.string.comments))
        displayBackButton(true)
    }
}