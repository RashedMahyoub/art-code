package com.snipertech.cryptobud.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.snipertech.cryptobud.R
import com.snipertech.cryptobud.db.entities.Message
import kotlinx.android.synthetic.main.item_list.view.*

class ItemRecyclerAdapter(private var items: LiveData<List<Message>>): RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {

    private lateinit var listener: OnItemClickListener
    private var itemList = ArrayList<Message>()

    init {
        if (items.value != null) {
            itemList = items.value as ArrayList<Message>
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList.get(position), listener)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_message = itemView.message
        val item_type = itemView.type
        val item_algo = itemView.algo
        val item_key = itemView.key

        fun bind(item: Message, listener: OnItemClickListener) {
            item_message.text = item.message
            item_type.text = item.type
            item_algo.text = item.algo
            item_key.text = item.key

            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    listener.onItemClick(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getMessageAt(position: Int): Message {
        return itemList.get(position)
    }

    interface OnItemClickListener {
        fun onItemClick(item: Message)
    }

    fun setOnClickLister(listener: OnItemClickListener) {
        this.listener = listener
    }
}