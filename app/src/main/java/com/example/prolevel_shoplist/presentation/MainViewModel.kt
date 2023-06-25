package com.example.prolevel_shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prolevel_shoplist.data.ShopListRepositoryImpl
import com.example.prolevel_shoplist.domain.DeleteShopItemUseCase
import com.example.prolevel_shoplist.domain.EditShopItemUseCase
import com.example.prolevel_shoplist.domain.GetShopListUseCase
import com.example.prolevel_shoplist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val shopListRepositoryImpl = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)

    val liveDataShopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        liveDataShopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnablesState(shopItem: ShopItem){
        val newItem = shopItem.copy(isEnabled = !shopItem.isEnabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

}