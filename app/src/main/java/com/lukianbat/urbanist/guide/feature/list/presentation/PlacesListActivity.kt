package com.lukianbat.urbanist.guide.feature.list.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukianbat.urbanist.guide.R

import com.lukianbat.urbanist.guide.databinding.ActivityPlaceListBinding
import com.lukianbat.urbanist.guide.feature.list.presentation.recycler.PlacesAdapter

import javax.inject.Inject
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.lukianbat.urbanist.guide.BR
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places

import com.lukianbat.urbanist.guide.feature.map.presentation.MapsActivity
import com.lukianbat.urbanist.guide.сore.App
import com.lukianbat.urbanist.guide.сore.presentation.activity.EventsActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_place_list.*


class PlacesListActivity :
    EventsActivity<ActivityPlaceListBinding, PlacesListViewModel, PlacesListViewModel.EventsListener>(),
    SearchView.OnQueryTextListener, PlacesListViewModel.EventsListener {
    override val eventsListener: PlacesListViewModel.EventsListener = this
    override val viewModelVariableId: Int = BR.viewModel

    @Inject
    override lateinit var viewModel: PlacesListViewModel

    private val adapter: PlacesAdapter = PlacesAdapter()

    override val layoutId = R.layout.activity_place_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        floatingActionAddNoteButton.hide()
        placeRecyclerView.adapter = adapter
        placeRecyclerView.layoutManager = layoutManager
        viewModel.liveData.observe(this, Observer {
            if (App.hasNetwork().not())
                routeToCacheMap()
            progress_message.visibility = View.GONE
            adapter.updateEvents(it)
        })
        adapter.checkPlaceList.observe(this, Observer {
            if (it.size in 2..5) {
                floatingActionAddNoteButton.show()
            } else {
                if (it.size > 5) {
                    eventsListener.showMessage(R.string.over_places_message)
                }
                floatingActionAddNoteButton.hide()
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

    @SuppressLint("DefaultLocale")
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            val searchList = arrayListOf<Place>()
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

    @SuppressLint("DefaultLocale")
    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            val searchList = arrayListOf<Place>()
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


    override fun routeToCacheMap() {
        val intent = Intent(applicationContext, MapsActivity::class.java)
        intent.putParcelableArrayListExtra("checkPlaces", viewModel.liveData.value)
        startActivity(intent)
    }

    @SuppressLint("CheckResult")
    override fun routeToMap() {
        val intent = Intent(applicationContext, MapsActivity::class.java)
        viewModel.setCash(Places(adapter.checkPlaceList.value))
            .subscribeOn(Schedulers.io())
            .subscribe {
                intent.putParcelableArrayListExtra(
                    "checkPlaces",
                    adapter.checkPlaceList.value
                )
                startActivity(intent)
            }
    }

    override fun showMessage(messageId: Int) {
        Snackbar.make(
            findViewById(android.R.id.content),
            resources.getString(messageId),
            Snackbar.LENGTH_SHORT
        )
            .show()

    }

}