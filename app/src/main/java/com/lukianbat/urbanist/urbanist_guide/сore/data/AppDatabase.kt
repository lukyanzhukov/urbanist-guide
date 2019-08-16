package com.lukianbat.urbanist.urbanist_guide.сore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lukianbat.urbanist.urbanist_guide.feature.list.data.CachePlacesDao
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Places
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.CacheRouteDao
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.RouteModel

@Database(entities = [Places::class, RouteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cashPlacesDao(): CachePlacesDao
    abstract fun cashRouteDao(): CacheRouteDao

}