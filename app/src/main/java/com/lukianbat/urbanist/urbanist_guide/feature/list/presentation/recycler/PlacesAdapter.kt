package com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ItemPlaceBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body

class PlacesAdapter : RecyclerView.Adapter<PlacesViewHolder>() {


    var placeList: List<Body> = arrayListOf()

    val onPlaceClickEvent = MutableLiveData<Body>()

    fun updateEvents(placeList: List<Body>) {
        this.placeList = placeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(inflater, R.layout.item_place, parent, false) as ItemPlaceBinding
        return PlacesViewHolder(binding)
    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        holder.binding?.place = placeList[position]
        holder.binding?.root?.setOnClickListener { onPlaceClickEvent.value = placeList[position] }
        holder.binding?.executePendingBindings()
    }
}

class PlacesViewHolder(binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

    var binding: ItemPlaceBinding? = binding
}