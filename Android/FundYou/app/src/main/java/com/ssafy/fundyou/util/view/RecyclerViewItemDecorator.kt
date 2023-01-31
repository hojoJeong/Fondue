package com.ssafy.fundyou.util.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils.dpToPx

class RecyclerViewItemDecorator(
    private val top: Int,
    private val bottom: Int,
    private val left: Int,
    private val right: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        val currentColumn = itemPosition % spanCount

        outRect.top = top
        outRect.bottom = bottom
        outRect.left = if(currentColumn != 0) left else 0
        outRect.right = right
    }
}