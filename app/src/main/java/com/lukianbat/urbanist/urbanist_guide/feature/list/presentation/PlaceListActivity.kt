package com.lukianbat.urbanist.urbanist_guide.feature.list.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ActivityPlaceListBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.recycler.PlacesAdapter
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_place_list.*
import javax.inject.Inject

class PlaceListActivity : BaseActivity<ActivityPlaceListBinding>() {

    @Inject
    lateinit var viewModel: PlaceListViewModel

    private val adapter: PlacesAdapter = PlacesAdapter()

    override val layoutId = R.layout.activity_place_list

    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        val layoutManager = LinearLayoutManager(this)
        placeRecyclerView.adapter = adapter
        placeRecyclerView.layoutManager = layoutManager
        viewModel.onBind()
        viewModel.liveData.observe(this, Observer {
            adapter.updateEvents(it)
        })
        adapter.onPlaceClickEvent.observe(this, Observer {
            Log.i("TAG", "click "+it.name)
        })

    }

}
