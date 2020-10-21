package com.flobiz.sample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flobiz.sample.constants.AppConstants.AD
import com.flobiz.sample.data.model.Country
import com.flobiz.sample.databinding.AdItemBinding
import com.flobiz.sample.databinding.CountryItemBinding

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.ViewHolder>(DiffCallback()) {

    companion object {
        private const val TYPE_COUNTRY = 0
        private const val TYPE_AD = 1
    }

    lateinit var onAdItemClick: ((Int) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_COUNTRY -> ViewHolder.forCountry(parent)
            else -> ViewHolder.forAD(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        when (item.cardType) {
            AD -> {
                holder.bindAdCard(item, object : ((Int) -> Unit) {
                    override fun invoke(position: Int) {
                        notifyItemRemoved(position)
                        onAdItemClick.invoke(position)
                    }
                })
            }
            else -> holder.bindCountryCard(item)
        }

    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCountryCard(item: Country) {
            binding as CountryItemBinding
            binding.country = item
            binding.executePendingBindings()
        }

        fun bindAdCard(item: Country, onItemDpadUpHit: (Int) -> Unit) {
            binding as AdItemBinding
            binding.ad = item
            binding.imgClose.setOnClickListener {
                onItemDpadUpHit.invoke(adapterPosition)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun forCountry(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CountryItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }

            fun forAD(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = AdItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].cardType == AD) {
            TYPE_AD
        } else {
            TYPE_COUNTRY
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}