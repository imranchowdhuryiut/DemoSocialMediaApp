package com.imran.demosocialmedia.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.imran.demosocialmedia.App
import com.imran.demosocialmedia.R
import com.imran.demosocialmedia.data.model.SubCategory
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_sub_category.view.*

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */
class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryViewHolder> () {

    private var mList: List<SubCategory>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        return SubCategoryViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        mList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    fun setList(subcatg: List<SubCategory>?) {
        mList = subcatg
        notifyDataSetChanged()
    }

}

class SubCategoryViewHolder (
    override val containerView: View
): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    fun bind(model: SubCategory) {
        containerView.tvSubCategoryname.text = model.subCategoryName

        if (App.appPref.getSavedCategories()?.contains(model.subCategoryName) == true) {
            containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_check_circle_24))
        } else {
            containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_add_circle_outline_24))
        }

        containerView.imgSelect.setOnClickListener {
            if (App.appPref.getSavedCategories()?.contains(model.subCategoryName) == true) {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_add_circle_outline_24))
                var items = App.appPref.getSavedCategories()
                model.subCategoryName?.let { it1 -> items?.toMutableList()?.remove(it1) }
                App.appPref.setSavedCategories(null)
                App.appPref.setSavedCategories(items)
            } else {
                containerView.imgSelect.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_baseline_check_circle_24))
                var items = mutableListOf<String>()
                model.subCategoryName?.let { it1 -> items.add(it1) }
                App.appPref.getSavedCategories()?.let { it1 -> items.addAll(it1) }
                App.appPref.setSavedCategories(items.toSet())
            }
        }
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup
        ): SubCategoryViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category, parent, false)
            return SubCategoryViewHolder(itemView)
        }
    }
}