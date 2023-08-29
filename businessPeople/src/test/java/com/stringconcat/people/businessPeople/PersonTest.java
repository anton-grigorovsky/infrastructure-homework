package com.stringconcat.people.businessPeople;

import org.junit.jupiter.api.Test;

import static com.stringconcat.people.businessPeople.Fixtures.AVATAR_URL;
import static com.stringconcat.people.businessPeople.Fixtures.BIRTH_DATE;
import static com.stringconcat.people.businessPeople.Fixtures.FIRST_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.PERSON_ID;
import static com.stringconcat.people.businessPeople.Fixtures.QUOTE;
import static com.stringconcat.people.businessPeople.Fixtures.SECOND_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.SEX;
import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    @Test
    public void createPersonSuccess() {
        var person = getPerson();

        assertEquals(person.getId(), PERSON_ID);
        assertEquals(person.getFirstName(), FIRST_NAME);
        assertEquals(person.getSecondName(), SECOND_NAME);
        assertEquals(person.getBirthDate(), BIRTH_DATE);
        assertEquals(person.getSex(), SEX);
        assertEquals(person.getAvatartUrl(), AVATAR_URL);
        assertEquals(person.getFavoriteQuote(), QUOTE);
    }

    @Test
    public void personIsMature() {
        var person = getPerson();
        assertTrue(person.mature(BIRTH_DATE.plusYears(41)));
    }

    @Test
    public void personIsNotMature() {
        var person = getPerson();
        assertFalse(person.mature(BIRTH_DATE.plusYears(35)));
    }

    @Test
    public void countAge() {
        var person = getPerson();
        assertEquals(40, person.age(BIRTH_DATE.plusYears(40)));
    }

    @Test
    public void changeAvatarSuccess() {
        var person = getPerson();
        var newAvatar = "https://new-avater";
        person.changeAvatar(newAvatar);
        assertEquals(person.getAvatartUrl(), newAvatar);
    }
}
