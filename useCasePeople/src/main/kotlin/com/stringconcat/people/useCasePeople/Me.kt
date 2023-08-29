package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person

interface Me {
    operator fun invoke(): Person
}