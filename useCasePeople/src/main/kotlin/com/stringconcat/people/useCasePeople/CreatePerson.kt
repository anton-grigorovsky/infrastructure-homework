package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.useCasePeople.scenarios.PersonCreationSummary

interface CreatePerson {
    operator fun invoke(personInput: PersonCreationSummary): Person
}