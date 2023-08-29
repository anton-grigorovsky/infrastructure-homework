package com.stringconcat.people.persistance

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.persistance.model.PersonEntity
import com.stringconcat.people.persistance.repository.PersonRepository
import com.stringconcat.people.useCasePeople.access.PersonExtractor
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonExtractorFromRepository(
        private val repository: PersonRepository
) : PersonExtractor {
    override fun getById(id: UUID): Person? =
            repository
                    .findById(id)
                    .toNullable()
                    ?.let { PersonEntity.toBusiness(it) }

}