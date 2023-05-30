package com.example.randomimageviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favorites")
data class RandomImage(
    @PrimaryKey
    val id: String,
    @SerialName(value = "url")
    @ColumnInfo(name = "url")
    val linkURL: String,
    val isLiked: Boolean = false,
    val width: Int? = null,
    val height: Int? = null
)
