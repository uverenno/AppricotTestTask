package com.example.appricottesttask.ui.list_characters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.appricottesttask.R
import com.example.appricottesttask.appComponent
import com.example.appricottesttask.databinding.FragmentCharactersBinding
import com.example.appricottesttask.ui.list_characters.adapter.CharactersAdapter
import com.example.appricottesttask.ui.list_characters.adapter.StateLoaderAdapter
import com.example.appricottesttask.ui.navigation.BundleKey
import com.example.appricottesttask.ui.navigation.Navigate
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)

    @Inject
    lateinit var factory: Lazy<CharactersViewModel.Factory>
    private val viewModel by viewModels<CharactersViewModel> { factory.get() }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CharactersAdapter(requireContext()) { id ->
            id?.let {
                val bundle = Bundle()
                bundle.putInt(BundleKey.CATEGORY_KEY, it)
                (activity as? Navigate)?.navigate(
                    R.id.action_charactersFragment_to_detailsFragment,
                    bundle
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = StateLoaderAdapter(),
                footer = StateLoaderAdapter()
            )
        }
        adapter.addLoadStateListener { state ->
            with(binding) {
                recyclerView.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.characters.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}