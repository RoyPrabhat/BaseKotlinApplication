package com.example.wellthydemoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wellthydemoapp.R
import com.example.wellthydemoapp.datamodel.Comment
import java.util.*

class CommentListAdapter(
    private val mCommentList: ArrayList<Comment>?,
    private val mContext: FragmentActivity?,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CommentListAdapter.MyViewHolder>() {

    interface ItemClickListener {
        fun onClick(message: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mComment.text = mCommentList!![position].body
        holder.mVotes.text = "Votes : " + mCommentList!![position].votes
        holder.mDate.text = "Posted on : " + mCommentList!![position].creationTime
        holder.bind(mCommentList!![position].body, itemClickListener);
    }

    override fun getItemCount(): Int {
        return mCommentList?.size ?: 0
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mComment: TextView
        var mVotes: TextView
        var mDate: TextView
        var mShare: ImageButton

        init {
            mComment = itemView.findViewById(R.id.comment)
            mVotes = itemView.findViewById(R.id.votes)
            mDate = itemView.findViewById(R.id.date)
            mShare = itemView.findViewById(R.id.share)
        }


        fun bind(message: String?, listener: ItemClickListener) {
            mShare.setOnClickListener { listener.onClick(message) }
        }
    }
}