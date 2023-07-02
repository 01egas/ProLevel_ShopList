package com.example.prolevel_shoplist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.prolevel_shoplist.domain.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.shopItemId == newItem.shopItemId
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}