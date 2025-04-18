package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID

@Entity(tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id"],
            childColumns = ["authorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CPlace::class,
            parentColumns = ["id"],
            childColumns = ["placeId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Post(
    @Json(name = "id")
    @PrimaryKey
    val id: UUID,

    @Json(name = "authorId")
    @ColumnInfo
    val authorId: UUID,

    @Json(name = "text")
    @ColumnInfo
    val text: String,

    @Json(name = "createdAt")
    @ColumnInfo
    val createdAt: Long,

    @Json(name = "placeId")
    @ColumnInfo
    val placeId: UUID?
    )