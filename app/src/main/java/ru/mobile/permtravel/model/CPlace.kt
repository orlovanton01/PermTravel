package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID

@Entity(tableName = "places")
data class CPlace (
    @Json(name = "id")
    @PrimaryKey
    var id : UUID,
    @Json(name = "name")
    @ColumnInfo
    var name : String,
    @Json(name = "photoPath")
    @ColumnInfo
    var photoPath: String,
    @ColumnInfo
    @Json(name = "description")
    var description: String,

    @Json(name = "latitude")
    @ColumnInfo
    var latitude: Double,

    @Json(name = "longitude")
    @ColumnInfo
    var longitude: Double
)