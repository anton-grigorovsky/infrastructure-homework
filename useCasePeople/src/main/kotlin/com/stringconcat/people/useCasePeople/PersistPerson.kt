package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person

fun interface PersistPerson {

    fun persist(person: Person)
}