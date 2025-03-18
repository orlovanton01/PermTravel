package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val instagramLink: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val avatar: String,

)
