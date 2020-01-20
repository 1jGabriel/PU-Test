package br.com.base.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.base.R
import br.com.base.data.model.Model
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter : PagedListAdapter<Model, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int) = MainViewHolder.create(view)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Model?) {
            item?.let {
                itemView.image.text = it.deal_id
            }
        }

        companion object {
            fun create(parent: ViewGroup) = MainViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item,
                    parent,
                    false
                )
            )
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(
                oldConcert: Model,
                newConcert: Model
            ) = oldConcert.deal_id == newConcert.deal_id

            override fun areContentsTheSame(
                oldConcert: Model,
                newConcert: Model
            ) = oldConcert.deal_id == newConcert.deal_id
        }
    }
}
