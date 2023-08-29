package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person
import java.util.*

interface GetPerson {
    operator fun invoke(id: UUID): Person?
}