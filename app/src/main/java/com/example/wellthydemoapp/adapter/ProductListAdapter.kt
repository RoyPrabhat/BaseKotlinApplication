package com.example.wellthydemoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.datamodel.Post
import com.squareup.picasso.Picasso

import java.util.ArrayList

class ProductListAdapter
//  private final ItemClickListener mListener;
    (private val mProdList: ArrayList<Post>?, private val mContext: FragmentActivity?)
    : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    interface ItemClickListener {
        fun onClick(dogName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mProdName.text = mProdList!![position].name
                Picasso.with(mContext).load(mProdList!![position].thumbnail!!.imageUrl)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .fit().into(holder.mProdImage)
              //  holder.bind(mBreedList.get(position), mListener);
    }

    override fun getItemCount(): Int {
        return mProdList?.size ?: 0
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var mProdName: TextView
        lateinit var mProdImage: ImageView

        init {
            mProdName = itemView.findViewById(R.id.prodName)
            mProdImage = itemView.findViewById(R.id.prodImage)
        }


        fun bind(dogName: String, listener: ItemClickListener) {
            itemView.setOnClickListener { listener.onClick(mProdName.text.toString()) }
        }
    }
}