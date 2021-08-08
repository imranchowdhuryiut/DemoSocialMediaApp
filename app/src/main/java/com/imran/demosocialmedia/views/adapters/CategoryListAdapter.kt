package com.imran.demosocialmedia.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.demosocialmedia.App
import com.imran.demosocialmedia.R
import com.imran.demosocialmedia.data.model.Category
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_category.view.imgSelect
import kotlinx.android.synthetic.main.item_sub_category.view.*

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */

class CategoryListAdapter: RecyclerView.Adapter<CategoryViewHolder> () {

    private var mList: List<Category>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        mList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    fun setData(categories: List<Category>?) {
        mList = categories
        notifyDataSetChanged()
    }


}

class CategoryViewHolder (
    override val containerView: View
): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    fun bind(category: Category) {
        containerView.tvCategoryname.text = category.categoryName
        containerView.imgArrow.setOnClickListener {
            if (containerView.rvSubCategory.isVisible) {
                containerView.imgArrow.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_arrow_forward_ios_24))
                containerView.rvSubCategory.visibility = View.GONE
            } else {
                containerView.imgArrow.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_keyboard_arrow_down_24))
                containerView.rvSubCategory.visibility = View.VISIBLE
            }
        }
        if (category.subcatg?.isNotEmpty() == true) {
            val list: MutableList<String> = category.subcatg?.map { it.subCategoryName ?: "" }?.toMutableList() ?: mutableListOf()
            if (App.appPref.getSavedCategories()?.containsAll(list) == true) {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_check_circle_24))
            } else {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_add_circle_outline_24))
            }
        }




        containerView.rvSubCategory.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
        val adapter = SubCategoryAdapter()
        containerView.rvSubCategory.adapter = adapter
        adapter.setList(category.subcatg)
        containerView.imgSelect.setOnClickListener {
            val list: MutableList<String> = category.subcatg?.map { it.subCategoryName ?: "" }?.toMutableList() ?: mutableListOf()
            if (App.appPref.getSavedCategories()?.containsAll(list) == true) {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_add_circle_outline_24))
                var items = App.appPref.getSavedCategories()?.toMutableList()
                items?.removeAll(list)
                App.appPref.setSavedCategories(null)
                App.appPref.setSavedCategories(items?.toSet())
            } else {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_check_circle_24))
                var items = mutableListOf<String>()
                items.addAll(list)
                App.appPref.setSavedCategories(items.toSet())
            }
        }
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup
        ): CategoryViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            return CategoryViewHolder(itemView)
        }
    }

}