package com.muco.myapplication3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muco.myapplication3.databinding.BreweryItemBinding

class BreweryAdapter: ListAdapter<BreweryModel, BreweryAdapter.BreweryViewHolder>(DiffCallback()) {

    class BreweryViewHolder(private val binding: BreweryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BreweryModel) {
            binding.apply {
                breweryTitle.text = item.name
                breweryLocation.text = item.city
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
       return BreweryViewHolder(
           BreweryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       )
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        val brewery = getItem(position)
        holder.bind(brewery)
    }
}

class DiffCallback : DiffUtil.ItemCallback<BreweryModel>() {
    override fun areItemsTheSame(oldItem: BreweryModel, newItem: BreweryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreweryModel, newItem: BreweryModel): Boolean {
        return oldItem == newItem
    }

}