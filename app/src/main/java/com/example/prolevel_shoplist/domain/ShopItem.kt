package com.example.prolevel_shoplist.domain

data class ShopItem(

    val name: String,
    val count: Int,
    var isEnabled: Boolean,
    var shopItemId: Int = UNDEFINED_ID

) {

    companion object{
        const val UNDEFINED_ID = -1
    }

}
