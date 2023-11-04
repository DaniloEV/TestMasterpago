package com.example.appmasterpago.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
/**No es con respecto a Retrofit si no más bien a recycler view, sirve para trabajar info dinamica que venga de algún lado */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}