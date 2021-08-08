package com.imran.demosocialmedia.data.network.services

import com.imran.demosocialmedia.data.model.CategoryModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */
interface CategoryService {

    @GET("/get_categories")
    suspend fun getCategoryList(): Response<CategoryModel?>

}