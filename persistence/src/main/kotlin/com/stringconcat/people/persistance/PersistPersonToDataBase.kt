package com.stringconcat.people.persistance

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.persistance.model.PersonEntity
import com.stringconcat.people.persistance.repository.PersonRepository
import com.stringconcat.people.useCasePeople.access.PersonPersister
import org.springframework.stereotype.Component

@Component
class PersistPersonToDataBase(
        private val repository: PersonRepository
): PersonPersister {
    override fun persist(person: Person) {
        repository
                .save(PersonEntity.fromBusiness(person))
                .let { PersonEntity.toBusiness(it) }
    }
}