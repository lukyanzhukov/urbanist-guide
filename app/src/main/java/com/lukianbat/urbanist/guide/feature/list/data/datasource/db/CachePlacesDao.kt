package com.lukianbat.urbanist.guide.feature.list.data.datasource.db

import androidx.room.*
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CachePlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlaces(places: Places): Completable

    @Query("SELECT * FROM places WHERE id = :id")
    fun getPlacesById(id: Long): Single<Places>

}
