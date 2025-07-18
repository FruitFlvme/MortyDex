package com.fruitflvme.mortydex.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponseDto(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)

@JsonClass(generateAdapter = true)
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
