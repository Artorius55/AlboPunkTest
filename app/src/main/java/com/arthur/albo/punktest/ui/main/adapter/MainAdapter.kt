package com.arthur.albo.punktest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthur.albo.punktest.R
import com.arthur.albo.punktest.data.model.Beer
import com.arthur.albo.punktest.databinding.RowBeerBinding
import com.bumptech.glide.Glide

class MainAdapter(private val listener: BeerItemListener) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    interface BeerItemListener {
        fun onClickedBeer(beer: Beer)
    }

    private val beers = ArrayList<Beer>()

    class DataViewHolder(private val itemBinding: RowBeerBinding, private val listener: MainAdapter.BeerItemListener) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        private lateinit var beer: Beer

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(beer: Beer) {
            this.beer = beer
            itemBinding.txtBeerName.text = beer.name
            itemBinding.txtBeerTagline.text = beer.tagline
            Glide.with(itemBinding.beerPhoto.context)
                .load(beer.imageUrl)
                .into(itemBinding.beerPhoto)
        }

        override fun onClick(v: View?) {
            listener.onClickedBeer(beer)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DataViewHolder {
        val binding: RowBeerBinding = RowBeerBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return DataViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(beers[position])
    }

    override fun getItemCount() = beers.size

    fun setBeers(beers: List<Beer>) {
        this.beers.clear()
        this.beers.addAll(beers)
        notifyDataSetChanged()
    }

}