package com.pvg.pvg2023.ui.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pvg.pvg2023.R
import com.pvg.pvg2023.databinding.ActivitySettingBinding
import com.pvg.pvg2023.model.UserInfo

/** 設定画面 */
class SettingActivity: AppCompatActivity(), SettingPresenter {

    lateinit var binding: ActivitySettingBinding

    /** ViewModel */
    private val viewModel: SettingViewModel by lazy {
        ViewModelProvider(this, SettingViewModel.Factory(application, this))[SettingViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.setProfileInfo()
        setList()
    }

    /**
     * リサイクルView設定
     */
    fun setList() {
        val itemList = Item.values().toList()
        val adapter = SettingListAdapter(this, itemList) {
            when (it) {
                Item.NOTIFICATION_FROM_PVG -> {}
                Item.BLOOD_KIT_REGISTER -> {}
                Item.NOTIFICATION_SETTING -> {}
                Item.MODEL_CHANGE -> {}
                Item.CONTACT_INFORMATION -> {}
                Item.TERMS_OF_SERVICE -> {}
                Item.PRIVACY_SETTING -> {}
            }
        }
        val recyclerView = binding.listViewSetting
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /** Setting Presenter Method */
    override fun setProfileText(userInfo: UserInfo) {
        binding.textName.text = userInfo.name
        binding.textUserId.text = String.format(getString(R.string.setting_id, userInfo.id))
        binding.textOfficeName.text = String.format(getString(R.string.setting_office, userInfo.office))
        binding.textInsurerName.text = String.format(getString(R.string.setting_insurance, userInfo.insurance))
    }

    /**
     * Item List Enum
     */
    enum class Item(val resId: Int) {
        NOTIFICATION_FROM_PVG(R.string.setting_notification_from_pvg),
        BLOOD_KIT_REGISTER(R.string.setting_blood_kit_register),
        NOTIFICATION_SETTING(R.string.setting_notification_setting),
        MODEL_CHANGE(R.string.setting_model_change),
        CONTACT_INFORMATION(R.string.setting_contact_information),
        TERMS_OF_SERVICE(R.string.setting_terms_of_service),
        PRIVACY_SETTING(R.string.setting_privacy_setting);
    }

    /**
     * Setting Adapter
     */
    inner class SettingListAdapter(
        /** コンテキスト */
        private val context: Context,
        /** ItemList */
        private val itemList: List<Item>,
        /** Item タップ時の処理 */
        private val onClickListener: (Item) -> Unit
    ): RecyclerView.Adapter<SettingListAdapter.ItemViewHolder>() {

        /**
         * Item View Holder
         */
        inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
            /** Item Title TextView */
            val textView: TextView = view.findViewById(R.id.text_item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.item_setting, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = itemList[position]
            holder.textView.text = context.getString(itemList[position].resId)
            holder.itemView.setOnClickListener {
                onClickListener(item)
            }
        }

        override fun getItemCount(): Int {
            return itemList.size
        }
    }
}