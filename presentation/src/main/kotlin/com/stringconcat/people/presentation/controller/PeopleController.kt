package com.stringconcat.people.presentation.controller

import com.stringconcat.people.presentation.API_ME
import com.stringconcat.people.presentation.API_PERSON_GENERATE
import com.stringconcat.people.presentation.API_PERSON_GET_BY_ID
import com.stringconcat.people.presentation.model.PersonRespectfullViewModel
import com.stringconcat.people.presentation.view.personDetailsForm
import com.stringconcat.people.presentation.view.renderDetailedView
import com.stringconcat.people.useCasePeople.CreatePerson
import com.stringconcat.people.useCasePeople.GetPerson
import com.stringconcat.people.useCasePeople.Me
import com.stringconcat.people.useCasePeople.scenarios.PersonCreationSummary
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.net.URI
import java.util.*

@Controller
class PeopleController(
    val getPerson: GetPerson,
    val createNew: CreatePerson,
    val getMe: Me
) {

    @RequestMapping(value = [API_ME], method = [RequestMethod.GET])
    @ResponseBody
    fun me(): String {
        return renderDetailedView(person = PersonRespectfullViewModel(getMe()))
    }

    @RequestMapping(value = [API_PERSON_GET_BY_ID])
    fun get(@PathVariable id: String): ResponseEntity<String> {
        val idUUD = try {
            UUID.fromString(id)
        } catch (ignored: IllegalArgumentException) {
            return ResponseEntity.badRequest().build()
        }

        val person = getPerson(idUUD)
            ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(
            renderDetailedView(PersonRespectfullViewModel(person))
        )
    }

    @RequestMapping(value = [API_PERSON_GENERATE], method = [RequestMethod.GET])
    @ResponseBody
    fun showCreationForm(): String {
        return personDetailsForm()
    }

    @RequestMapping(
        value = [API_PERSON_GENERATE],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    @ResponseBody
    fun create(personInput: PersonCreationSummary): ResponseEntity<String> {
        val generatedPerson = createNew(personInput)

        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create("/id/${generatedPerson.id}"))
            .build()
    }
}