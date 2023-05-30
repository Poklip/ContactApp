package com.example.contacts

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.MainActivity.Companion.id
import com.example.contacts.databinding.ItemContactBinding


class ContactsAdapter(private var activity: Activity) :
    ListAdapter<Contact, ContactsAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(contact: Contact?) {
            binding.tvNamePlusSurname.text = "${contact?.name} ${contact?.surname}"
            binding.tvNumber.text = contact?.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener {
            with(activity) {
                id = getItem(position).id
                startActivity(Intent(this, EditContactActivity::class.java))
            }
        }
    }

    fun setData(allContacts: List<Contact>) {
        this.submitList(allContacts)
        notifyDataSetChanged()
    }
}