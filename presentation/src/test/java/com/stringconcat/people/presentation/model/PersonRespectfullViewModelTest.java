package com.stringconcat.people.presentation.model;

import com.stringconcat.people.businessPeople.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonRespectfullViewModelTest {

    @Test
    public void createViewSuccess() {
        var person = getPerson();
        var view = new PersonRespectfullViewModel(person);
        assertEquals(person.getAvatartUrl(), view.avatarUrl());
        assertEquals(person.getBirthDate().getDayOfMonth() + " " +
                person.getBirthDate().getMonth() + " " +
                person.getBirthDate().getYear(),
                view.birthDate());
        assertEquals(person.getFavoriteQuote(), view.favoriteQuote());
        assertEquals(" " + person.getFirstName() + " " + person.getSecondName(), view.title());
    }

    @Test
    public void getMatureManRightRespectfulTreatment() {
        var person = getPerson(LocalDate.of(1970, 12, 12), Person.Sex.MAN);
        var view = new PersonRespectfullViewModel(person);
        assertEquals("Mr " + person.getFirstName() + " " + person.getSecondName(), view.title());
    }

    @Test
    public void getMatureWomanRightRespectfulTreatment() {
        var person = getPerson(LocalDate.of(1970, 12, 12), Person.Sex.WOMAN);
        var view = new PersonRespectfullViewModel(person);
        assertEquals("Mrs " + person.getFirstName() + " " + person.getSecondName(), view.title());
    }
}
