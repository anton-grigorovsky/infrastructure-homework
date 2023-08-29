package com.stringconcat.people.useCasePeople.access

import com.stringconcat.people.businessPeople.Person
import java.util.*

fun interface PersonExtractor {

    fun getById(id: UUID): Person?
}