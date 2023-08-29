package com.stringconcat.people.useCasePeople.access

import com.stringconcat.people.businessPeople.Person

fun interface PersonPersister {

    fun persist(person: Person)
}