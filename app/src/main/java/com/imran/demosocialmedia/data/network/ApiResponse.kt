package com.imran.demosocialmedia.data.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */

data class ApiResponse<T>(
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: T? = null,

)