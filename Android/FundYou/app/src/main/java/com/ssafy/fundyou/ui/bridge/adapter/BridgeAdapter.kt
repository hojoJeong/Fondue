package com.ssafy.fundyou.ui.bridge.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R

class BridgeAdapter(private val mContext: Context):
    RecyclerView.Adapter<BridgeAdapter.BridgeViewHolder>() {

    var mActivityList = arrayListOf<Class<out Activity>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_bridge_list, parent, false)
        return BridgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BridgeViewHolder, position: Int) {
        holder.bind(mActivityList[position])
    }

    override fun getItemCount(): Int {
        return mActivityList.size
    }

    fun addItems(activityList: List<Class<out Activity>>){
        mActivityList.addAll(activityList)
    }

    class BridgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var className: TextView
        fun bind(activity: Class<out Activity>) {
            className = itemView.findViewById(R.id.tv_item)

            className.text = activity.simpleName
            className.setOnClickListener {
                it.context.startActivity(Intent(it.context, activity))
            }
        }
    }
}