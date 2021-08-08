package com.imran.demosocialmedia.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imran.demosocialmedia.App
import com.imran.demosocialmedia.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_selected_category.view.*
import kotlinx.android.synthetic.main.item_sub_category.view.*

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */
class SelectedListAdapter : RecyclerView.Adapter<SelectedViewHolder>() {

    private var mList: MutableList<String>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        return SelectedViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        mList?.get(position)?.let {
            holder.bind(it) {pos->
                mList?.removeAt(pos)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    fun setData(categories: MutableList<String>?) {
        mList = categories
        notifyDataSetChanged()
    }
}


class SelectedViewHolder(
    override val containerView: View
) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    fun bind(model: String, onRemove: (position: Int) -> Unit) {
        containerView.tvCategoryname.text = model
        containerView.imgCross.setOnClickListener {
            val list = App.appPref.getSavedCategories()?.toMutableList()
            list?.remove(model)
            App.appPref.setSavedCategories(list?.toSet())
            onRemove.invoke(adapterPosition)
        }
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup
        ): SelectedViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_selected_category, parent, false)
            return SelectedViewHolder(itemView)
        }
    }
}