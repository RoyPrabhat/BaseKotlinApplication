package com.example.wellthydemoapp.view.comment

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.adapter.CommentListAdapter
import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.datamodel.Comment
import com.example.wellthydemoapp.util.WhatsAppUtil
import com.example.wellthydemoapp.viewmodel.CommentListViewModel
import com.example.wellthydemoapp.viewmodel.ViewModelFactory
import javax.inject.Inject


class CommentListFragment : Fragment() {

    private var mCommentList: ArrayList<Comment>? = null
    private var mCommentRecyclerView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var mCommentListViewModel: CommentListViewModel? = null
    private var mCommentListAdapter: CommentListAdapter? = null
    private var mPrevious: Button? = null
    private var mNext: Button? = null
    private lateinit var productId : String
    val PRODUCT_ID = "PRODUCT_ID"

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        (activity!!.application as MyApplication)
            .applicationComponent!!
            .inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.wellthydemoapp.R.layout.fragment_comment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mCommentRecyclerView = view!!.findViewById(com.example.wellthydemoapp.R.id.commentList)
        mProgressBar = view!!.findViewById(com.example.wellthydemoapp.R.id.commentProgressBar)
        mCommentList = ArrayList()
        val args = arguments
        productId = args!!.getString(PRODUCT_ID,"")
        initializeViewModel()
        initializeRecyclerView()
        initializeObserver()
        setUpButton()
    }

    private fun initializeViewModel() {
        mCommentListViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(CommentListViewModel::class.java)
    }

    private fun initializeRecyclerView() {
        mCommentListAdapter = CommentListAdapter(mCommentList, activity,
            object : CommentListAdapter.ItemClickListener {
                override fun onClick(message: String?) {
                    WhatsAppUtil.sendWhatAppMessage(activity, message)
                }

            })

        mCommentRecyclerView!!.setAdapter(mCommentListAdapter)
        mCommentRecyclerView!!.setLayoutManager(LinearLayoutManager(activity))
    }

    private fun initializeObserver() {
        mProgressBar!!.visibility = View.VISIBLE
        mCommentListViewModel!!.getComments(productId, CommentListViewModel.RequestType.NEXT)
            ?.observe(viewLifecycleOwner, Observer { newList ->
                if (newList != null) {
                    updateProductList(newList)
                }
            })
    }

    private fun setUpButton() {
        mPrevious = view!!.findViewById(com.example.wellthydemoapp.R.id.previous)
        mNext = view!!.findViewById(com.example.wellthydemoapp.R.id.next)

        mPrevious!!.setOnClickListener { _ ->
            mProgressBar!!.visibility = View.VISIBLE
            mCommentListViewModel!!.getComments(productId, CommentListViewModel.RequestType.PREV)
        }

        mNext!!.setOnClickListener { _ ->
            mProgressBar!!.visibility = View.VISIBLE
            mCommentListViewModel!!.getComments(productId, CommentListViewModel.RequestType.NEXT)
        }

    }

    private fun updateProductList(newList: ArrayList<Comment>) {
        if (newList.size > 0) {
            mProgressBar!!.visibility = View.GONE
        }
        mCommentList!!.clear();
        mCommentList!!.addAll(newList);
        mCommentListAdapter!!.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCommentList!!.clear();
        mCommentListAdapter!!.notifyDataSetChanged()
    }

}