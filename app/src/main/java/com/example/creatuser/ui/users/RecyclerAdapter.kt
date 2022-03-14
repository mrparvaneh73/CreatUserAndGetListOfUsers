package com.example.creatuser.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creatuser.data.local.model.User
import com.example.creatuser.databinding.ListItemBinding

class RecyclerAdaptor(val items: List<User>) : RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder>() {


    lateinit var itemClick: ItemClick

    inner class MyViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(pos:Int) {
           binding.firstname.text = items[pos].firstName
            binding.lastname.text=items[pos].lastName
            binding.nationalcode.text=items[pos].nationalCode

        }
        init {
            binding.root.setOnClickListener(this);
        }
        override fun onClick(p0: View?) {
            itemClick.viewClick(adapterPosition, p0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptor.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListItemBinding.inflate(inflater, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdaptor.MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ItemClick{
        fun viewClick(position: Int,v:View?)
    }

    fun setItemUserClick(itemclick:ItemClick){
        this.itemClick = itemclick
    }
}