package com.example.prolevel_shoplist.data

import com.example.prolevel_shoplist.domain.ShopItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        itemId = shopItem.itemId,
        name = shopItem.name,
        count = shopItem.count,
        isEnabled = shopItem.isEnabled
    )

    fun mapDbModelToMapEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        itemId = shopItemDbModel.itemId,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        isEnabled = shopItemDbModel.isEnabled
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToMapEntity(it)
    }

}