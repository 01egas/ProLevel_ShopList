package com.example.prolevel_shoplist.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val isEnabled: Boolean
)
