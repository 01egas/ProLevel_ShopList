package com.example.prolevel_shoplist.presentation

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.prolevel_shoplist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveDataShopList.observe(this) { list ->
            shopListAdapter.submitList(list)
            Log.d("MainActivityList", "MainActivityList ${list.toString()}")
        }

        val btnAdd = findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd)
        btnAdd.setOnClickListener {
            val nIntent = ShopItemActivity.newIntentAddItem(this)
            startActivity(nIntent)
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rvShopList)
        shopListAdapter = ShopListAdapter()
        with(rvShopList) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE_VIEW_HOLDER
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE_VIEW_HOLDER
            )

            setupLongClickListener()

            setupClickListener()

            setupSwipeListener(rvShopList)

        }

    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = { shopItem ->
            val nIntent = ShopItemActivity.newIntentEditItem(this, shopItem.shopItemId)
            startActivity(nIntent)

            Log.d("MainActivityItemClick", "shopitem $shopItem")
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = { shopItem ->
            viewModel.changeEnablesState(shopItem)
        }
    }

}

