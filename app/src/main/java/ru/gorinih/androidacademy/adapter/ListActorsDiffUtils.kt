package ru.gorinih.androidacademy.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.gorinih.androidacademy.data.Actor

class ListActorsDiffUtils : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }
}