package ru.mobile.permtravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id"],
            childColumns = ["authorId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Post(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo
    val authorId: UUID,
    @ColumnInfo
    val text: String,
    @ColumnInfo
    val createdAt: Long
    )