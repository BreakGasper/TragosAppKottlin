package com.breakgasper.tragosappkottlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breakgasper.tragosappkottlin.R
import com.breakgasper.tragosappkottlin.base.BaseViewHolder
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.databinding.TragosRowBinding
import com.bumptech.glide.Glide
/* corrregir mi codigo ya que algo fallla en el repo
**/
class MainAdapter(
    private val context: Context, private val tragosList: List<Drink>,
    private val itemClickListener: OnTragoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener {
        fun onTragoClick(drink: Drink, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            val binding = TragosRowBinding.bind(itemView)

            Glide.with(context).load(item.imagen).centerCrop().into(binding.ivFoto)
            binding.tvDescripcion.text = item.descrpcion
            binding.tvNombre.text = item.nombre

            itemView.setOnClickListener { itemClickListener.onTragoClick(item,position) }
        }
    }
}