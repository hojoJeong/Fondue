package com.ssafy.fundyou.ui.common.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.DialogCommonBinding

class CommonDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCommonBinding
    private lateinit var positiveButtonClickEvent: (Long) -> Unit
    private var id: Long = 0
    private var title: String = ""
    private var content: String = ""
    private var positiveButtonText = ""
    private var negativeButtonText = ""
    private var positiveButtonVisibility = View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate<DialogCommonBinding?>(layoutInflater, R.layout.dialog_common, null, false).apply {
            tvDialogTitle.text = title
            tvDialogContent.text = content
            btnNegative.text = negativeButtonText
            btnPositive.text = positiveButtonText
            btnPositive.visibility = positiveButtonVisibility
        }
        setContentView(binding.root)
        initButtonEvent()
    }

    fun initDialog(
        id: Long = 0,
        title: String,
        content: String,
        positiveButtonText: String = "",
        negativeButtonText: String,
        positiveButtonVisibility: Boolean,
        event: (Long) -> Unit,
    ) {
        this.id = id
        this.positiveButtonClickEvent = event
        this.title = title
        this.content = content
        this.positiveButtonText = positiveButtonText
        this.negativeButtonText = negativeButtonText
        this.positiveButtonVisibility = if (positiveButtonVisibility) View.VISIBLE else View.GONE
    }

    private fun initButtonEvent() {
        binding.btnNegative.setOnClickListener {
            dismiss()
        }

        binding.btnPositive.setOnClickListener {
            positiveButtonClickEvent.invoke(id)
            dismiss()
        }
    }
}