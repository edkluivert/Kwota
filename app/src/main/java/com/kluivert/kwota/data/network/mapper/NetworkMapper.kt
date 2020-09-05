package com.kluivert.kwota.data.network.mapper

import com.kluivert.kwota.data.network.model.NetworkQuoteEntity
import com.kluivert.kwota.domain.entities.Quote
import com.kluivert.kwota.domain.util.EntityMapper
import javax.inject.Inject

class NetworkMapper

 @Inject
 constructor() : EntityMapper<NetworkQuoteEntity, Quote> {
    override fun mapFromEntity(entity: NetworkQuoteEntity): Quote {

        return Quote(
            author = entity.author,
            text = entity.text
        )

    }

    override fun mapToEntity(domainModel: Quote): NetworkQuoteEntity {

        return NetworkQuoteEntity(
            author = domainModel.author,
            text = domainModel.text
        )

    }


    fun mapFromEntityList(entity: List<NetworkQuoteEntity>):List<Quote>{
        return entity.map { mapFromEntity(it) }
    }


}


