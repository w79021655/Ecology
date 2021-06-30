package com.sample.ecology.ui.animal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.ecology.R
import com.sample.ecology.data.animal.AnimalAreaItem

class AnimalAreaListAdapter(_listener: OnItemClickListener) : RecyclerView.Adapter<AnimalAreaListAdapter.ZoosViewHolder>() {
    private var mContext: Context? = null
    private var listener = _listener
    private var list = ArrayList<AnimalAreaItem>()

    interface OnItemClickListener {
        fun onItemClick(
            eName: String,
            ePicURL: String,
            eInfo: String,
            eMemo: String,
            eCategory: String,
            eURL: String
        )
    }

    fun setAnimalArea(list: ArrayList<AnimalAreaItem>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoosViewHolder {
        mContext = parent.context.applicationContext
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cell_ecological_area, parent, false)
        return ZoosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZoosViewHolder, position: Int) {
        val item = list[position]
        Glide.with(mContext!!).load(item.ePicURL).into(holder.image)
        holder.zooName.text = item.eName
        holder.info.text = item.eInfo

        if (item.eMemo == "") {
            holder.restInfo.text = mContext!!.getString(R.string.not_info)
        } else {
            holder.restInfo.text = item.eMemo
        }

        holder.layout.setOnClickListener {
            listener.onItemClick(
                item.eName,
                item.ePicURL,
                item.eInfo,
                item.eMemo,
                item.eCategory,
                item.eURL
            )
        }
    }

    inner class ZoosViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var layout: ConstraintLayout = itemView!!.findViewById(R.id.layout)
        var image: ImageView = itemView!!.findViewById(R.id.image)
        var zooName: TextView = itemView!!.findViewById(R.id.zoo_name)
        var info: TextView = itemView!!.findViewById(R.id.info)
        var restInfo: TextView = itemView!!.findViewById(R.id.rest_info)
    }
}