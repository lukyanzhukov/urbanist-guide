package com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ItemPlaceBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Place
import android.util.SparseBooleanArray


class PlacesAdapter : RecyclerView.Adapter<PlacesViewHolder>() {


    private var placeList: ArrayList<Place> = arrayListOf()
    var checkPlaceList = MutableLiveData<ArrayList<Place>>()
    private val itemStateArray = SparseBooleanArray()
    fun updateEvents(placeList: ArrayList<Place>) {
        this.placeList = placeList
        checkPlaceList.value = arrayListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(inflater, R.layout.item_place, parent, false) as ItemPlaceBinding
        return PlacesViewHolder(binding)
    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        if (placeList[position].name.isNullOrEmpty().not()) {
            if (!itemStateArray.get(position, false)) {
                holder.binding?.imageView?.setBackgroundResource(R.drawable.ic_not_check_circle_24dp)
            } else {
                holder.binding?.imageView?.setBackgroundResource(R.drawable.ic_check_circle_24dp)
            }
            holder.binding?.place = placeList[position]
            holder.binding?.root?.setOnClickListener {
                if (!itemStateArray.get(position, false)) {
                    holder.binding?.imageView?.setBackgroundResource(R.drawable.ic_check_circle_24dp)
                    checkPlaceList.value?.add(placeList[position])
                    checkPlaceList.postValue(checkPlaceList.value)
                    itemStateArray.put(position, true)
                } else {
                    holder.binding?.imageView?.setBackgroundResource(R.drawable.ic_not_check_circle_24dp)
                    checkPlaceList.value?.remove(placeList[position])
                    checkPlaceList.postValue(checkPlaceList.value)
                    itemStateArray.put(position, false);
                }
            }
            holder.binding?.executePendingBindings()
        }
    }

}

class PlacesViewHolder(binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

    var binding: ItemPlaceBinding? = binding
}