package com.example.prolevel_shoplist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.prolevel_shoplist.R
import com.example.prolevel_shoplist.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = ShopItem.UNDEFINED_ID
//
//    private lateinit var tilName : TextInputLayout
//    private lateinit var etName : EditText
//    private lateinit var tilCount : TextInputLayout
//    private lateinit var etCount : EditText
//    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        addTextChangeListeners()
//        launchRightMode()
//        observeViewModel()
    }

//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_ADD -> launchAddMode()
//            MODE_EDIT -> launchEditMode()
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this){ shopItem ->
//            etName.setText(shopItem.name)
//            etCount.setText(shopItem.count.toString())
//        }
//        btnSave.setOnClickListener{
//            viewModel.editShopItem(etName.text.toString(), etCount.text.toString())
//        }
//    }
//
//    private fun launchAddMode() {
//        btnSave.setOnClickListener{
//            viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
//        }
//    }
//
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
//
//    private fun parseIntent(){
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException(getString(R.string.screen_mode_absent_exception))
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_ADD && mode != MODE_EDIT) {
//            throw RuntimeException(getString(R.string.unknown_screen_mode) + mode)
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException(getString(R.string.shop_item_absent_exception))
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//    }
//
//    private fun initViews(){
//        tilName = findViewById(R.id.til_name)
//        etName = findViewById(R.id.et_name)
//        tilCount = findViewById(R.id.til_count)
//        etCount = findViewById(R.id.et_count)
//        btnSave = findViewById(R.id.btn_save)
//    }
//
//    private fun addTextChangeListeners(){
//        etName.addTextChangedListener(object :TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s != null) {
//                    viewModel.resetInputErrorName()
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })
//
//        etCount.addTextChangedListener(object :TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s != null) {
//                    viewModel.resetInputErrorCount()
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//        })
//    }
//
//    private fun observeViewModel(){
//        viewModel.errorInputName.observeForever {
//            if (it == true) {
//                tilName.error = getString(R.string.name_error)
//            } else {
//                tilName.error = null
//            }
//        }
//
//        viewModel.errorInputCount.observeForever{
//            if (it == true) {
//                tilCount.error = getString(R.string.count_error)
//            } else {
//                tilCount.error = null
//            }
//        }
//
//        viewModel.shouldCloseScreen.observe(this){
//            finish()
//        }
//    }

}