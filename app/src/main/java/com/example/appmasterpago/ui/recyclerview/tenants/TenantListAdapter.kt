package com.example.appmasterpago.ui.recyclerview.tenants

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appmasterpago.core.BaseViewHolder
import com.example.appmasterpago.databinding.ItemTenantBinding
import com.example.appmasterpago.models.api_data.TenantItemData
import com.squareup.picasso.Picasso

//Interfaz
/**
 * Interfaz que le dice que acciones quiero tener en mi vista, la hereda el adapter
 * */
interface OnTenantListClickListener {
    /**Cuando se le de click a un elemento que le coloque el observable*/
    fun onTenantClick(tenant: TenantItemData, position: Int)

    fun onTenantLongClick(tenant: TenantItemData, position: Int)
}

/**
 * Adapter que se va a utilizar en el fragment para hacer binding de la
 * información al final el adapter funcionaria como item
 * */
class  TenantListAdapter (
    private val context: Context,
    private val itemClickListener:  OnTenantListClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    var listaProductos: MutableList<TenantItemData> = mutableListOf()

    //Setea la lista y notifica que debe cambiar segun la info que traiga
    fun setTenantList(listaProductosParam: MutableList<TenantItemData>) {
        this.listaProductos = listaProductosParam
        notifyDataSetChanged()
    }

    /** Al momento de crear el view holder le indica las posicion para colocar que accion va a realizar*/
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BaseViewHolder<*>  {
        /**Le indica que vista va a colocar y va a ser su binding*/
        val itemBinding =
            ItemTenantBinding.inflate(LayoutInflater.from(context), parent, false)
        //Le infica que viewHolder va a utilizar y le realiza el binding del item
        val holder = ProductoListViewHolder(itemBinding)
        //region ListenerClick

        /**Le coloca un listener al botón y le indica que accion sigue*/
        itemBinding.btnGoToNextPage.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onTenantClick(listaProductos[position], position)
        }

        //En caso de que le apliquen un longClick realizar alguna acción
        holder.itemView.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnLongClickListener true
            itemClickListener.onTenantLongClick(listaProductos[position], position)
            return@setOnLongClickListener true
        }
        //endregion
        return holder
    }

    override fun getItemCount(): Int = listaProductos.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ProductoListViewHolder -> holder.bind(listaProductos[position])
        }

    }


    /**Indica como realizar el binding según el layout o la vista que sigue al inicio, es como un template del item*/
    private inner class ProductoListViewHolder(
        private val binding: ItemTenantBinding
    ) : BaseViewHolder<TenantItemData>(binding.root) {
        override fun bind(item: TenantItemData) = with(binding) {
            binding.txtTitulo.text = item.tenancyName
            Picasso.get()
                .load(item.image)
                .resize(180, 180)
                .onlyScaleDown().into(binding.imgTenant);
        }
    }

}