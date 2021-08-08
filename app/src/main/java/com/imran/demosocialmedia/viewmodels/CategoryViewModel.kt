package com.imran.demosocialmedia.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.imran.demosocialmedia.data.model.CategoryModel
import com.imran.demosocialmedia.data.network.ApiResponse
import com.imran.demosocialmedia.data.network.LiveDataResource
import com.imran.demosocialmedia.data.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */


class CategoryViewModel : ViewModel() {

    private val mRepo: CategoryRepository by lazy { CategoryRepository() }

    fun getCategoryList(): LiveData<LiveDataResource<ApiResponse<CategoryModel>>> {
        return requestApiData {
            mRepo.fetchCategoryList()
        }
    }

    private fun <T> requestApiData(
        processData: ((T?) -> Unit)? = null,
        requestApiResponse: suspend () -> ApiResponse<T>?
    ): LiveData<LiveDataResource<ApiResponse<T>>> {
        return liveData(Dispatchers.Default) {
            emit(LiveDataResource.loading())
            val data = requestApiResponse()
            if (data?.status == true) {
                processData?.invoke(data.data)
                emit(LiveDataResource.success(data))
            } else emit(LiveDataResource.error(data))
        }
    }
}