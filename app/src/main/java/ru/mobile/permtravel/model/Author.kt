package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID

@Entity(tableName = "authors")
data class Author(
    @Json(name = "id")
    @PrimaryKey
    val id: UUID,

    @Json(name = "name")
    @ColumnInfo
    val name: String,

    @Json(name = "instagramLink")
    @ColumnInfo
    val instagramLink: String,

    @Json(name = "description")
    @ColumnInfo
    val description: String,

    @Json(name = "avatar")
    @ColumnInfo
    var avatar: String,

)
