package com.example.prolevel_shoplist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.prolevel_shoplist.R
import com.example.prolevel_shoplist.databinding.ItemShopDisabledBinding
import com.example.prolevel_shoplist.databinding.ItemShopEnabledBinding
import com.example.prolevel_shoplist.domain.ShopItem

class ShopListAdapter() :
    ListAdapter<ShopItem, ShopListViewHolder>(ShopItemDiffCallback()) {

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 2

        const val MAX_POOL_SIZE_VIEW_HOLDER = 15
    }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {

        val itemLayout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            itemLayout,
            parent,
            false
        )
        return ShopListViewHolder(binding)

    }


    override fun onBindViewHolder(viewHolder: ShopListViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopDisabledBinding ->{
                binding.shopItem = shopItem
            }
            is ItemShopEnabledBinding ->{
                binding.shopItem = shopItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isEnabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

}