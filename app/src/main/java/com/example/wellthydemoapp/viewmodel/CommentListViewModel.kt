package com.example.wellthydemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wellthydemoapp.datamodel.Comment
import com.example.wellthydemoapp.repository.CommentsRepository
import java.util.*
import javax.inject.Inject

class CommentListViewModel @Inject constructor(val commentsRepository: CommentsRepository, var pageNumber: Int = 0) :
    ViewModel() {

    fun getComments(productId: String, requestType: RequestType): LiveData<ArrayList<Comment>>? {

        if (requestType == RequestType.NEXT) {
            return commentsRepository.getComments(productId, (++pageNumber).toString())
        } else {
            --pageNumber
            if (pageNumber > 0) {
                return commentsRepository.getComments(productId, (pageNumber).toString())
            } else {
                return null
            }
        }
    }

    enum class RequestType {
        NEXT,
        PREV
    }
}