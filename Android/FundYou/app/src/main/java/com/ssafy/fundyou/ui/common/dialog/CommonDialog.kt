package com.ssafy.fundyou.ui.common.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.DialogCommonBinding

class CommonDialog(context : Context) : Dialog(context) {

    private lateinit var binding : DialogCommonBinding
    private lateinit var positiveButtonClickEvent : (Long) -> Unit
    private var id : Long = 0
    private var title : String = ""
    private var content : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_common, null, false)
        setContentView(binding.root)
        binding.tvDialogTitle.text = title
        binding.tvDialogContent.text = content
        initButtonEvent()
    }

    fun initDialog(id : Long = 0, title : String, content : String, event : (Long) -> Unit){
        this.id = id
        positiveButtonClickEvent = event
        this.title = title
        this.content = content
    }

    private fun initButtonEvent(){
        binding.btnNegative.setOnClickListener {
            dismiss()
        }

        binding.btnPositive.setOnClickListener {
            positiveButtonClickEvent.invoke(id)
            dismiss()
        }
    }
}