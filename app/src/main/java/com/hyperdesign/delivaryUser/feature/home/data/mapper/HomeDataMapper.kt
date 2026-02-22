package com.hyperdesign.delivaryUser.feature.home.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.feature.home.data.model.dto.HomeDto
import com.hyperdesign.delivaryUser.feature.home.domain.models.HomeData
import com.hyperdesign.delivaryUser.feature.orders.base.data.mappers.OrderMapper

object HomeDataMapper : Mapper<HomeDto, HomeData, Unit>() {
    override fun dtoToDomain(model: HomeDto): HomeData =
        HomeData(
            message = model.message.orEmpty(),
            ads = AdMapper.dtoToDomain(model.ads),
            orders = OrderMapper.dtoToDomain(model.orders)
        )
}