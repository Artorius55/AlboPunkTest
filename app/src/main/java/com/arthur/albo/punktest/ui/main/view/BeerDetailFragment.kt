package com.arthur.albo.punktest.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arthur.albo.punktest.R
import com.arthur.albo.punktest.data.model.Beer
import com.arthur.albo.punktest.databinding.FragmentBeerDetailBinding
import com.arthur.albo.punktest.utils.autoCleared
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailFragment : Fragment() {

    private var binding: FragmentBeerDetailBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var beer = arguments?.getParcelable<Beer>("beer")
        bindCharacter(beer)
    }

    private fun bindCharacter(beer: Beer?) {
        beer?.let {
            binding.name.text = beer.name
            binding.descriptionText.text = beer.description
            binding.firstBrewedTxt.text = beer.firstBrewed
            binding.abvTxt.text = beer.abv.toString()
            binding.ibuTxt.text = beer.ibu.toString()
            binding.phTxt.text = beer.ph.toString()
            binding.tipsTxt.text = beer.brewerTips

            Glide.with(binding.root)
                .load(beer.imageUrl)
                .into(binding.image)
        }

    }
}