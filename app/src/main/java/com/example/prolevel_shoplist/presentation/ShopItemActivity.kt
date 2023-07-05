package com.example.prolevel_shoplist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.prolevel_shoplist.R

class ShopItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        val id = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, 0)
        Log.d("ShopItemActivity", mode.toString() + " " + id.toString())

    }

    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"

        fun newIntentAddItem(context: Context): Intent{
            val nIntent = Intent(context, ShopItemActivity::class.java)
            nIntent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return nIntent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val nIntent = Intent(context, ShopItemActivity::class.java)
            nIntent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            nIntent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return nIntent
        }

    }
}