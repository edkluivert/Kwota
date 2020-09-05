package com.kluivert.kwota.data.cache.mapper

import com.kluivert.kwota.data.cache.model.LocalQuoteEntity
import com.kluivert.kwota.domain.entities.Quote
import com.kluivert.kwota.domain.util.EntityMapper
import javax.inject.Inject

class CacheMapper

@Inject
constructor() : EntityMapper<LocalQuoteEntity, Quote> {
    override fun mapFromEntity(entity: LocalQuoteEntity): Quote {
        return Quote(
            author = entity.author,
            text = entity.text
        )
    }

    override fun mapToEntity(domainModel: Quote): LocalQuoteEntity {
      return LocalQuoteEntity(
          author = domainModel.author,
          text = domainModel.text,
          id = 0
      )
    }

    fun mapFromEntityList(entity: List<LocalQuoteEntity>) : List<Quote>{
        return entity.map { mapFromEntity(it) }
    }
}