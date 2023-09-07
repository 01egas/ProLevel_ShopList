package com.example.prolevel_shoplist.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.prolevel_shoplist.domain.ShopItem
import com.example.prolevel_shoplist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDao = AppDataBase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.itemId)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItemById(shopItemId)
        return mapper.mapDbModelToMapEntity(dbModel)
    }

    override fun getShopList() = shopListDao.getShopList()
    

}