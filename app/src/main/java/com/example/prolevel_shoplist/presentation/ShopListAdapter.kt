package com.example.prolevel_shoplist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prolevel_shoplist.R
import com.example.prolevel_shoplist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter() : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    companion object{
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 2

        const val  MAX_POOL_SIZE_VIEWHOLDER = 15
    }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null

    interface OnShopItemLongClickListener{
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvCount = itemView.findViewById<TextView>(R.id.tvCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {

        val itemLayout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            itemLayout,
            parent,
            false
        )
        return ShopListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].isEnabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

}