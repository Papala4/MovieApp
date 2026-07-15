package com.space.remote.mapper

import com.space.model.GenreResponse
import com.space.remote.dto.GenreDto

class GenreMapper {
    fun map(dto: GenreDto) = GenreResponse(
        id = dto.id,
        name = dto.name
    )
}
