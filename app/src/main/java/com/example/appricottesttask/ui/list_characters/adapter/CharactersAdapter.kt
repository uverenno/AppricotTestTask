package com.example.appricottesttask.ui.list_characters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.CharacterItemBinding
import com.example.data.model.Character

class CharactersAdapter(
    context: Context,
    val itemClick : (id : Int?) -> Unit
) :
    PagingDataAdapter<Character, CharacterViewHolder>(CharacterDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)



    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClick(getItem(position)?.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(layoutInflater.inflate(R.layout.character_item, parent, false))
    }
}

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(CharacterItemBinding::bind)

    fun bind(character: Character?) {
        with(viewBinding) {
            character?.let {
                Glide.with(root).load(character.image).centerCrop().into(image)
                name.text = character.name
                species.text = character.species
                gender.text = character.gender
            }
        }
    }

}

private object CharacterDiffItemCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }
}