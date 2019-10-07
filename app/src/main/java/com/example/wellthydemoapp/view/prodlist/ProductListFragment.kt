package com.example.wellthydemoapp.view.prodlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.adapter.ProductListAdapter
import com.example.wellthydemoapp.adapter.ProductListAdapter.ItemClickListener
import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.component.DatePickerFragment
import com.example.wellthydemoapp.datamodel.Post
import com.example.wellthydemoapp.util.DateUtil
import com.example.wellthydemoapp.view.comment.CommentsListActivity
import com.example.wellthydemoapp.viewmodel.ProdListViewModel
import com.example.wellthydemoapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class ProductListFragment : Fragment() {

    private var mProdList: ArrayList<Post>? = null
    private var mBreedRecyclerView: RecyclerView? = null
    private var mProdListAdapter: ProductListAdapter? = null
    private var mProductListViewModel: ProdListViewModel? = null
    private var mProgressBar: ProgressBar? = null
    private var mSelectDate: Button? = null
    private var mSelectedDate: TextView? = null
    val COLUMN_COUNT = 2
    val REQUEST_CODE = 11
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
        return inflater.inflate(com.example.wellthydemoapp.R.layout.fragment_product_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mBreedRecyclerView = view!!.findViewById(com.example.wellthydemoapp.R.id.prodList)
        mProgressBar = view!!.findViewById(com.example.wellthydemoapp.R.id.progressBar)
        mSelectDate = view!!.findViewById(com.example.wellthydemoapp.R.id.selectDate)
        mSelectedDate = view!!.findViewById(com.example.wellthydemoapp.R.id.selectedDate)
        mSelectedDate!!.text = DateUtil.currentDate
        mProdList = ArrayList()
        initializeViewModel()
        initializeRecyclerView()
        initializeObserver()
        setUpButton()
    }

    private fun setUpButton() {
        mSelectDate!!.setOnClickListener { _ ->
            val newFragment = DatePickerFragment()
            newFragment.setTargetFragment(this, REQUEST_CODE)
            newFragment.show(fragmentManager!!, "DatePicker")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedDate = data!!.getStringExtra("selectedDate")
            mSelectedDate!!.text = selectedDate
            mProgressBar!!.visibility = View.VISIBLE
            mProductListViewModel!!.getProductList(selectedDate, activity!!.applicationContext)
        }
    }

    private fun initializeViewModel() {
        mProductListViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(ProdListViewModel::class.java)
    }

    private fun initializeRecyclerView() {
        mProdListAdapter = ProductListAdapter(mProdList, activity,
            object : ItemClickListener {
                override fun onClick(productId: String) {
                    val intent = Intent(activity, CommentsListActivity::class.java)
                    intent.putExtra(PRODUCT_ID, productId)
                    startActivity(intent)
                }

            })

        mBreedRecyclerView!!.setAdapter(mProdListAdapter)
        mBreedRecyclerView!!.setLayoutManager(GridLayoutManager(activity, COLUMN_COUNT))
    }

    private fun initializeObserver() {

        mProductListViewModel!!.getProductList(DateUtil.currentDate, activity!!.applicationContext).observe(viewLifecycleOwner, Observer { newList ->
            if (newList != null) {
                updateProductList(newList)
            }
        })
    }

    private fun updateProductList(newList: ArrayList<Post>) {
        if (newList.size > 0) {
            mProgressBar!!.visibility = View.GONE
        }
        mProdList!!.clear();
        mProdList!!.addAll(newList);
        mProdListAdapter!!.notifyDataSetChanged()
    }


}
