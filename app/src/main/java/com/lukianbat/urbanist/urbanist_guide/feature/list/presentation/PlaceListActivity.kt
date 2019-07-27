package com.lukianbat.urbanist.urbanist_guide.feature.list.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ActivityPlaceListBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.recycler.PlacesAdapter
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_place_list.*
import javax.inject.Inject

import android.view.Menu
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body
import com.lukianbat.urbanist.urbanist_guide.feature.map.presentation.MapActivity


class PlaceListActivity : BaseActivity<ActivityPlaceListBinding>(), SearchView.OnQueryTextListener {

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
        viewModel.setEventListener(eventsListener)
        viewModel.liveData.observe(this, Observer {
            progressBar.visibility = View.GONE
            adapter.updateEvents(it)
        })
        adapter.checkPlaceList.observe(this, Observer {
            if (it.size > 2) {
                floatingActionAddNoteButton.isClickable = true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Поиск"
        searchView.setOnQueryTextListener(this)
        searchView.isIconified = false

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            val searchList = arrayListOf<Body>()
            viewModel.liveData.value?.forEach {
                if (it.name.isNullOrEmpty().not())
                    if (it.name.toLowerCase().contains(query.toLowerCase())) {
                        searchList.add(it)
                    }
            }
            adapter.updateEvents(searchList)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            val searchList = arrayListOf<Body>()
            viewModel.liveData.value?.forEach {
                if (it.name.isNullOrEmpty().not())
                    if (it.name.toLowerCase().contains(newText.toLowerCase())) {
                        searchList.add(it)
                    }

            }
            adapter.updateEvents(searchList)
        }
        if (newText.isNullOrEmpty() && viewModel.liveData.value != null) {
            adapter.updateEvents(viewModel.liveData.value!!)
        }
        return true
    }

    private val eventsListener: PlaceListViewModel.EventsListener = object : PlaceListViewModel.EventsListener {
        override fun routeToMap() {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }

        override fun showMessage(message: String) {

        }
    }

}
