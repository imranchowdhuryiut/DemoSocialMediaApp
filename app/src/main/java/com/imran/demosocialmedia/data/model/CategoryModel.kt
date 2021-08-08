package com.imran.demosocialmedia.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */




data class CategoryModel(
    @SerializedName("categories")
    var categories: List<Category>? = null
)

data class Category(
    @SerializedName("category_id")
    var categoryId: String? = null,
    @SerializedName("category_name")
    var categoryName: String? = null,
    @SerializedName("subcatg")
    var subcatg: List<SubCategory>? = null
)

data class SubCategory(
    @SerializedName("sub_category_id")
    var subCategoryId: String? = null,
    @SerializedName("sub_category_name")
    var subCategoryName: String? = null
)