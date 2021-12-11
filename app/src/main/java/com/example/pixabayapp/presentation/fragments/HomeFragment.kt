package com.example.pixabayapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.pixabayapp.adapters.HitsAdapter
import com.example.pixabayapp.adapters.decorations.MarginItemDecoration
import com.example.pixabayapp.databinding.FragmentHomeBinding
import com.example.pixabayapp.domain.models.SearchHit
import com.example.pixabayapp.domain.utils.Status
import com.example.pixabayapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import extensions.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), HitsAdapter.OnClickItemListener {
    private lateinit var binding: FragmentHomeBinding

    private val vm: HomeViewModel by viewModels()
    private var list = emptyList<SearchHit>()

    @Inject
    lateinit var glide: RequestManager

    private val mAdapter by lazy { HitsAdapter(glide, this, requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        vm.searchStateFlow
            .onEach {
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        list = it.data?.hits!!
                        renderList(list)
                        Log.e("TAG", "$list")
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }

                }
            }.launchWhenStarted(lifecycleScope)

        return binding.root

    }

    private fun setupRecyclerView() {
        binding.rvResult.apply {
            addItemDecoration(MarginItemDecoration(16))
            adapter = mAdapter
            setHasFixedSize(false)

        }


    }

    private fun renderList(result: List<SearchHit>) {
        mAdapter.setData(result)
    }

    override fun onClick(searchHit: SearchHit) {
        Toast.makeText(context, searchHit.tags, Toast.LENGTH_SHORT).show()
    }
}
