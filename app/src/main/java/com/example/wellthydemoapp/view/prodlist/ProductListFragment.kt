package com.example.wellthydemoapp.view.prodlist

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.adapter.ProductListAdapter
import com.example.wellthydemoapp.datamodel.Post
import androidx.lifecycle.ViewModelProviders
import com.example.wellthydemoapp.base.MyApplication
import com.example.wellthydemoapp.viewmodel.ProdListViewModel
import com.example.wellthydemoapp.viewmodel.ViewModelFactory
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.GridLayoutManager




class ProductListFragment : Fragment(){

    private var mProdList: ArrayList<Post>? = null
    private var mBreedRecyclerView: RecyclerView? = null
    private var mProdListAdapter: ProductListAdapter? = null
    private var mProductListViewModel : ProdListViewModel? = null
    val COLUMN_COUNT = 2
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
        //setHasOptionsMenu(true)
        return inflater.inflate(com.example.wellthydemoapp.R.layout.fragment_product_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mBreedRecyclerView = view!!.findViewById(com.example.wellthydemoapp.R.id.prodList)
        mProdList = ArrayList()
        initializeViewModel()
        initializeRecyclerView()
        initializeObserver()
    }

    private fun initializeViewModel() {
        mProductListViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(ProdListViewModel::class.java!!)

        mProductListViewModel!!.getProductList();
    }

    private fun initializeRecyclerView() {
        mProdListAdapter = ProductListAdapter(mProdList, activity)


//            object : BreedListAdapter.ItemClickListener() {
//
//                fun onClick(dogName: String) {
//                    val intent = Intent(activity, ImageListActivity::class.java)
//                    intent.putExtra(Constants.DOG_NAME, dogName)
//                    startActivity(intent)
//                }
//            }

        mBreedRecyclerView!!.setAdapter(mProdListAdapter)
        mBreedRecyclerView!!.setLayoutManager(GridLayoutManager(activity, COLUMN_COUNT))
    }

    private fun initializeObserver() {

        mProductListViewModel!!.getProductList().observe(viewLifecycleOwner, Observer { newList ->
            if (newList != null) {
                updateProductList(newList)
            }
        })
    }

    private fun updateProductList(newList: ArrayList<Post>) {
        mProdList!!.clear();
        mProdList!!.addAll(newList);
        mProdListAdapter!!.notifyDataSetChanged()

    }


}
