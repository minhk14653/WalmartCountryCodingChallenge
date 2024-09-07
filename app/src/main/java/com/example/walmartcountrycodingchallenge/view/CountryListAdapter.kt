package com.example.walmartcountrycodingchallenge.view

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartcountrycodingchallenge.model.Country
import com.example.walmartcountrycodingchallenge.R
import com.example.walmartcountrycodingchallenge.databinding.CountryItemBinding

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.CountryItemViewHolder>(
    DiffCallback()
)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(
            CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CountryItemViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.countryTitle.text = item.name
            binding.countryCode.text = item.code
            binding.countryCapital.text = if (item.capital.isNullOrEmpty()) {
                "No Capital"
            } else item.capital
            item.region?.let {
                val builder = StringBuilder()
                builder.append(item.name).append(", ").append(item.region)
                binding.countryTitle.text = builder.toString()
            }
            val colorChangeAnimation = AnimatorInflater.loadAnimator(itemView.context, R.animator.color_change) as ObjectAnimator
            colorChangeAnimation.target = binding.countryTitle
            colorChangeAnimation.start()
        }
    }
}



class DiffCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }
}