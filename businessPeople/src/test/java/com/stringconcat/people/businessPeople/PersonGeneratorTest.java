package com.stringconcat.people.businessPeople;

import org.junit.jupiter.api.Test;

import static com.stringconcat.people.businessPeople.Fixtures.AVATAR_URL;
import static com.stringconcat.people.businessPeople.Fixtures.BIRTH_DATE;
import static com.stringconcat.people.businessPeople.Fixtures.FIRST_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.QUOTE;
import static com.stringconcat.people.businessPeople.Fixtures.SECOND_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.SEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonGeneratorTest {

    @Test
    public void generatePersonSuccess() {
        var personGenerator = new PersonGenerator(new Fixtures.MockQuoteProvider(), new Fixtures.MockAvatarProvider());

        var person = personGenerator.generate(FIRST_NAME, SECOND_NAME, BIRTH_DATE, Person.Sex.MAN);
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals(person.getFirstName(), FIRST_NAME);
        assertEquals(person.getSecondName(), SECOND_NAME);
        assertEquals(person.getBirthDate(), BIRTH_DATE);
        assertEquals(person.getSex(), SEX);
        assertEquals(person.getAvatartUrl(), AVATAR_URL);
        assertEquals(person.getFavoriteQuote(), QUOTE);
    }
}
