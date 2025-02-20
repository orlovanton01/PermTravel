package ru.mobile.permtravel.model

import java.util.UUID

data class CPlace (
    var id : UUID,
    var name : String,
    var photo: Int,
    var description: String
)