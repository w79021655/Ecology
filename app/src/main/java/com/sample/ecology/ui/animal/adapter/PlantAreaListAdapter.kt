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
import com.sample.ecology.data.plant.PlantItem

class PlantAreaListAdapter(_listener: OnItemClickListener) : RecyclerView.Adapter<PlantAreaListAdapter.PlantViewHolder>() {
    private var mContext: Context? = null
    private var listener = _listener
    private var list = ArrayList<PlantItem>()

    interface OnItemClickListener {
        fun onItemClick(
            fPic01URL: String,
            fNameCh: String,
            fNameEn: String,
            fAlsoKnown: String,
            fBrief: String,
            fFeature: String,
            fFunction: String,
            fUpdate: String
        )
    }

    fun setPlantList(list: ArrayList<PlantItem>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAreaListAdapter.PlantViewHolder {
        mContext = parent.context.applicationContext
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cell_ecological_area, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantAreaListAdapter.PlantViewHolder, position: Int) {
        val item = list[position]
        Glide.with(mContext!!).load(item.fPic01URL).into(holder.image)
        holder.plantName.text = item.fNameCh
        holder.info.text = item.fAlsoKnown

        holder.restInfo.visibility = View.INVISIBLE

        holder.layout.setOnClickListener {
            listener.onItemClick(
                item.fPic01URL,
                item.fNameCh,
                item.fNameEn,
                item.fAlsoKnown,
                item.fBrief,
                item.fFeature,
                item.fFunction,
                item.fUpdate
            )
        }
    }

    inner class PlantViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var layout: ConstraintLayout = itemView!!.findViewById(R.id.layout)
        var image: ImageView = itemView!!.findViewById(R.id.image)
        var plantName: TextView = itemView!!.findViewById(R.id.zoo_name)
        var info: TextView = itemView!!.findViewById(R.id.info)
        var restInfo: TextView = itemView!!.findViewById(R.id.rest_info)
    }
}