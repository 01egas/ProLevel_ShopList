package com.example.prolevel_shoplist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int,
    val name: String,
    val count: Int,
    var isEnabled: Boolean
)
