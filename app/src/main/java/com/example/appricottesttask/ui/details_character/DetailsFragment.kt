package com.example.appricottesttask.ui.details_character

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.appricottesttask.R
import com.example.appricottesttask.appComponent
import com.example.appricottesttask.databinding.FragmentDetailsBinding
import com.example.appricottesttask.ui.navigation.BundleKey
import dagger.Lazy
import javax.inject.Inject


class DetailsFragment : Fragment(R.layout.fragment_details) {

    @Inject
    lateinit var factory : Lazy<DetailsViewModel.Factory>
    private val viewModel by viewModels<DetailsViewModel> { factory.get() }

    private val binding by viewBinding (FragmentDetailsBinding::bind)

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(BundleKey.CATEGORY_KEY)
        id?.let {
            viewModel.getCharacter(it)
        }
        viewModel.character.observe(viewLifecycleOwner){
            character -> character?.let {
                with(binding){
                    name.text = character.name
                    gender.text = character.gender
                    episodes.text = character.episode.size.toString()
                    status.text = character.status
                    species.text = character.species
                    location.text = character.location.name
                    Glide.with(root).load(character.image).centerCrop().into(image)
                }
        }
        }
    }
}