package com.hyperdesign.delivaryUser.feature.home.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.home.data.model.dto.AdDto
import com.hyperdesign.delivaryUser.feature.home.domain.models.Ad

object AdMapper : Mapper<AdDto, Ad, Unit>() {
    override fun dtoToDomain(model: AdDto): Ad = Ad(
        id = model.id.orEmpty(),
        title = model.title.orEmpty(),
        orderBy = model.orderBy.orEmpty(),
        lang = model.lang.orEmpty(),
        image = model.image.orEmpty()
    )
}