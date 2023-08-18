package com.example.prolevel_shoplist.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.prolevel_shoplist.R
import com.example.prolevel_shoplist.databinding.FragmentShopItemBinding
import com.example.prolevel_shoplist.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment(

) : Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private var _viewBinding: FragmentShopItemBinding? = null
    private val viewBinding: FragmentShopItemBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentShopItemBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        Log.d("ShopItemFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ShopItemFragment", "onCreateView")
        _viewBinding = FragmentShopItemBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ShopItemFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    override fun onAttach(context: Context) {
        Log.d("ShopItemFragment", "onAttach")
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw java.lang.RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }


    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(viewLifecycleOwner) { shopItem ->
//            viewBinding.etName.setText(shopItem.name)
//            viewBinding.etCount.setText(shopItem.count.toString())
//        }
        viewBinding.btnSave.setOnClickListener {
            viewModel.editShopItem(
                viewBinding.etName.text.toString(),
                viewBinding.etCount.text.toString()
            )
        }
    }

    private fun launchAddMode() {
        viewBinding.btnSave.setOnClickListener {
            viewModel.addShopItem(
                viewBinding.etName.text.toString(),
                viewBinding.etCount.text.toString()
            )
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException(getString(R.string.screen_mode_absent_exception))
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException(getString(R.string.unknown_screen_mode) + mode)
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException(getString(R.string.shop_item_absent_exception))
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }


    private fun addTextChangeListeners() {
        viewBinding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    viewModel.resetInputErrorName()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        viewBinding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    viewModel.resetInputErrorCount()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun observeViewModel() {
//        viewModel.errorInputName.observe(viewLifecycleOwner) {
//            if (it == true) {
//                viewBinding.tilName.error = getString(R.string.name_error)
//            } else {
//                viewBinding.tilName.error = null
//            }
//        }
//
//        viewModel.errorInputCount.observe(viewLifecycleOwner) {
//            if (it == true) {
//                viewBinding.tilCount.error = getString(R.string.count_error)
//            } else {
//                viewBinding.tilCount.error = null
//            }
//        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

}
