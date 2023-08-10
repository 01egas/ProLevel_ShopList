package com.example.prolevel_shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prolevel_shoplist.R
import com.example.prolevel_shoplist.domain.ShopItem

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException(getString(R.string.unknown_screen_mode) + screenMode)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment) //важно заменять а не добавлять, чтобы лишний раз не вызывать метод oncreate
            .commit()
    }

    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""
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

    private fun parseIntent(){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException(getString(R.string.screen_mode_absent_exception))
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException(getString(R.string.unknown_screen_mode) + mode)
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException(getString(R.string.shop_item_absent_exception))
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    override fun onEditingFinished() {
        finish()
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }


}