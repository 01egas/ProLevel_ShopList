package com.example.prolevel_shoplist.domain

import androidx.lifecycle.MutableLiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): MutableLiveData<List<ShopItem>> {

        return shopListRepository.getShopList()

    }

}