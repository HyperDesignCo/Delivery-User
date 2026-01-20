package com.example.delivaryUser.feature.home.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.home.data.model.dto.AdsDto
import com.example.delivaryUser.feature.home.domain.models.Ads

object AdsMapper : Mapper<AdsDto, Ads, Unit>() {
    override fun dtoToDomain(model: AdsDto): Ads =
        Ads(message = model.message.orEmpty(), ads = AdMapper.dtoToDomain(model.ads))
}