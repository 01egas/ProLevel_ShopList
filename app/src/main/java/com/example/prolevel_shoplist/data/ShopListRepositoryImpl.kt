package com.example.prolevel_shoplist.data

import androidx.lifecycle.MutableLiveData
import com.example.prolevel_shoplist.domain.ShopItem
import com.example.prolevel_shoplist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.itemId.compareTo(o2.itemId)})
    private var autoIncrementId = 0
    private val liveDataShopList = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0..100) {
            val item = ShopItem("name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.itemId == ShopItem.UNDEFINED_ID) {
            shopItem.itemId = autoIncrementId++
        }
        shopList.add(shopItem)
        updateLiveData()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLiveData()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemById(shopItem.itemId)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find {
            it.itemId == shopItemId
        } ?: throw RuntimeException("Element with $shopItemId not found")
    }

    override fun getShopList(): MutableLiveData<List<ShopItem>> {
        return liveDataShopList
    }

    private fun updateLiveData() {
        liveDataShopList.value = shopList.toList()
    }


}