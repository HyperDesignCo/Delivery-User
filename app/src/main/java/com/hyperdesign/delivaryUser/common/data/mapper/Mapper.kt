package com.hyperdesign.delivaryUser.common.data.mapper

abstract class Mapper<Dto, Domain, Entity> {
    abstract fun dtoToDomain(model: Dto): Domain

    fun dtoToDomain(list: List<Dto>?): List<Domain> = (list ?: emptyList()).map(::dtoToDomain)

    open fun dtoToEntity(model: Dto): Entity =
        throw NotImplementedError("override and implement this method")

    fun dtoToEntity(list: List<Dto>): List<Entity> = list.map(::dtoToEntity)

    open fun domainToDto(model: Domain): Dto =
        throw NotImplementedError("override and implement this method")

    fun domainToDto(list: List<Domain>): List<Dto> = (list).map(::domainToDto)

    open fun domainToEntity(model: Domain): Entity =
        throw NotImplementedError("override and implement this method")

    fun domainToEntity(list: List<Domain>): List<Entity> = list.map(::domainToEntity)
    open fun entityToDto(model: Entity): Dto =
        throw NotImplementedError("override and implement this method")

    fun entityToDto(list: List<Entity>): List<Dto> = list.map(::entityToDto)

    open fun entityToDomain(model: Entity): Domain =
        throw NotImplementedError("override and implement this method")

    fun entityToDomain(list: List<Entity>): List<Domain> = list.map(::entityToDomain)
}