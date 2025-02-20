package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "places")
data class CPlace (
    @PrimaryKey
    var id : UUID,
    @ColumnInfo
    var name : String,
    @ColumnInfo
    var photo: Int,
    @ColumnInfo
    var description: String
)