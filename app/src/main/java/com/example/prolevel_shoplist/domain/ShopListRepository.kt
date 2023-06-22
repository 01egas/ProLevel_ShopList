package com.example.prolevel_shoplist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemById(id: Int): ShopItem

    fun getShopList(): List<ShopItem>

}