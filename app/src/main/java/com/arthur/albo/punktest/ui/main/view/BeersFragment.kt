package com.arthur.albo.punktest.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthur.albo.punktest.R
import com.arthur.albo.punktest.data.model.Beer
import com.arthur.albo.punktest.databinding.FragmentBeersBinding
import com.arthur.albo.punktest.ui.main.adapter.MainAdapter
import com.arthur.albo.punktest.ui.main.viewmodel.BeersViewModel
import com.arthur.albo.punktest.utils.Resource
import com.arthur.albo.punktest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeersFragment : Fragment(), MainAdapter.BeerItemListener {

    private var binding: FragmentBeersBinding by autoCleared()
    private val viewModel: BeersViewModel by viewModels()
    private lateinit var adapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter(this)
        binding.beersRV.layoutManager = LinearLayoutManager(requireContext())
        binding.beersRV.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.beers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setBeers(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedBeer(beer: Beer) {
        findNavController().navigate(
            R.id.action_beersFragment_to_beerDetailFragment,
            bundleOf("beer" to beer)
        )
    }

}