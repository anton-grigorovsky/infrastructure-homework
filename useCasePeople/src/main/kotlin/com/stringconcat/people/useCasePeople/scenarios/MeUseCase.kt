package com.stringconcat.people.useCasePeople.scenarios

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.useCasePeople.Me
import com.stringconcat.people.useCasePeople.access.PersonPersister
import java.time.LocalDate
import java.util.*
import javax.inject.Named

private const val MY_BIRTH_YEAR = 1987
private const val MY_BIRTH_MONTH = 12
private const val MY_BIRTH_DAY = 1

@Named
class MeUseCase(
    private val personPersister: PersonPersister
): Me {
    override fun invoke(): Person {
        val me = Person(
            id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
            firstName = "Sergey",
            secondName = "Bukharov",
            birthDate = LocalDate.of(MY_BIRTH_YEAR, MY_BIRTH_MONTH, MY_BIRTH_DAY),
            sex = Person.Sex.MAN,
            avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
            favoriteQuote = "make the easy things easy, and the hard things possible"
        )
        personPersister.persist(me)
        return me
    }
}