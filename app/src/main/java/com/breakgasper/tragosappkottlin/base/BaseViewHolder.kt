package com.breakgasper.tragosappkottlin.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.breakgasper.tragosappkottlin.databinding.TragosRowBinding

abstract class BaseViewHolder<T>(itemView: View ) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T, position: Int)
}