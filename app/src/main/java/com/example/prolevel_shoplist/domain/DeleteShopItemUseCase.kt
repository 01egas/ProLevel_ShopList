package com.example.prolevel_shoplist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository)  {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }

}