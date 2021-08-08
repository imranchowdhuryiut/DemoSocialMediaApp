package com.imran.demosocialmedia.data.repository

import com.imran.demosocialmedia.data.model.CategoryModel
import com.imran.demosocialmedia.data.network.ApiClient.createApiService
import com.imran.demosocialmedia.data.network.ApiResponse
import com.imran.demosocialmedia.data.network.services.CategoryService

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */

class CategoryRepository {

    private val mWebService = createApiService<CategoryService>()

    suspend fun fetchCategoryList(): ApiResponse<CategoryModel> {
        val response = mWebService.getCategoryList()
        return if (response.isSuccessful) {
            ApiResponse(true, "", response.body())
        } else {
            ApiResponse(false, "Something went wrong", null)
        }
    }

}