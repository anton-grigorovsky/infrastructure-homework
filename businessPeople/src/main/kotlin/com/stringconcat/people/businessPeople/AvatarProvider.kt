package com.stringconcat.people.businessPeople

fun interface AvatarProvider {
    fun createForPerson(person: Person): String
}
