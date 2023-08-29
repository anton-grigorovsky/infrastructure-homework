package com.stringconcat.people.useCasePeople.scenarios

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.useCasePeople.GetPerson
import com.stringconcat.people.useCasePeople.access.PersonExtractor
import java.util.*
import javax.inject.Named

@Named
class GetPersonUseCase(
        private val personExtractor: PersonExtractor
) : GetPerson {

    override fun invoke(id: UUID): Person? = personExtractor.getById(id)
}