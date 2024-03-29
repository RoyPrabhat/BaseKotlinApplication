package com.example.wellthydemoapp.view.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.adapter.CommentListAdapter
import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.constant.Constants.Companion.PRODUCT_ID
import com.example.wellthydemoapp.datamodel.Comment
import com.example.wellthydemoapp.util.ToastUtil
import com.example.wellthydemoapp.util.WhatsAppUtil
import com.example.wellthydemoapp.viewmodel.CommentListViewModel
import com.example.wellthydemoapp.viewmodel.ViewModelFactory
import javax.inject.Inject


class CommentListFragment : Fragment() {

    private var mCommentList: ArrayList<Comment>? = null
    private lateinit var productId: String

    private var mCommentRecyclerView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var mPrevious: Button? = null
    private var mNext: Button? = null

    private var mCommentListViewModel: CommentListViewModel? = null
    private var mCommentListAdapter: CommentListAdapter? = null

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
        initialize()
        initializeRecyclerView()
        initializeObserver()
        setUpButtonActions()
    }

    private fun initialize() {
        mCommentRecyclerView = view!!.findViewById(com.example.wellthydemoapp.R.id.commentList)
        mProgressBar = view!!.findViewById(com.example.wellthydemoapp.R.id.commentProgressBar)
        mPrevious = view!!.findViewById(com.example.wellthydemoapp.R.id.previous)
        mNext = view!!.findViewById(com.example.wellthydemoapp.R.id.next)
        mCommentList = ArrayList()
        val args = arguments
        productId = args!!.getString(PRODUCT_ID, "")
        mCommentListViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(CommentListViewModel::class.java)
    }


    private fun initializeRecyclerView() {
        mCommentListAdapter = CommentListAdapter(mCommentList, activity,
            object : CommentListAdapter.ItemClickListener {
                override fun onClick(message: String) {
                    WhatsAppUtil.sendWhatAppMessage(activity, message)
                }

            })

        mCommentRecyclerView!!.setAdapter(mCommentListAdapter)
        mCommentRecyclerView!!.setLayoutManager(LinearLayoutManager(activity))
    }

    private fun initializeObserver() {
        mProgressBar!!.visibility = View.VISIBLE
        mCommentListViewModel!!.getComments(productId, CommentListViewModel.RequestType.NEXT,
            activity!!.applicationContext)
            ?.observe(viewLifecycleOwner, Observer { newList ->
                if (newList != null && newList.size > 0) {
                    updateProductList(newList)
                } else {
                    showNoDataAvailableView()
                }
            })
    }

    private fun setUpButtonActions() {
        mPrevious!!.setOnClickListener { _ ->
            mProgressBar!!.visibility = View.VISIBLE
            mCommentListViewModel!!.getComments(
                productId,
                CommentListViewModel.RequestType.PREV,
                activity!!.applicationContext
            )
        }

        mNext!!.setOnClickListener { _ ->
            mProgressBar!!.visibility = View.VISIBLE
            mCommentListViewModel!!.getComments(
                productId,
                CommentListViewModel.RequestType.NEXT,
                activity!!.applicationContext
            )
        }
    }

    private fun updateProductList(newList: ArrayList<Comment>) {
        mProgressBar!!.visibility = View.GONE
        mCommentList!!.clear();
        mCommentList!!.addAll(newList);
        mCommentListAdapter!!.notifyDataSetChanged()
    }

    private fun showNoDataAvailableView() {
        mProgressBar!!.visibility = View.GONE
        ToastUtil.showToast(activity, getString(R.string.no_new_comments))
    }

}