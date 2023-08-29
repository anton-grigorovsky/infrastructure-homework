package com.stringconcat.people.businessPeople;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class Fixtures {

    public static final UUID PERSON_ID = UUID.randomUUID();
    public static final String FIRST_NAME = "John";
    public static final String SECOND_NAME = "Wick";
    public static final String QUOTE = "Favorite quote";
    public static final String AVATAR_URL = "https://url";
    public static final LocalDate BIRTH_DATE = LocalDate.of(1988, 8, 8);
    public static final Person.Sex SEX = Person.Sex.MAN;

    public static Person getPerson() {
        return new Person(
                PERSON_ID,
                FIRST_NAME,
                SECOND_NAME,
                BIRTH_DATE,
                SEX,
                AVATAR_URL,
                QUOTE
        );
    }

    public static Person getPerson(LocalDate birthDate, Person.Sex sex) {
        return new Person(
                PERSON_ID,
                FIRST_NAME,
                SECOND_NAME,
                birthDate,
                sex,
                AVATAR_URL,
                QUOTE
        );
    }

    public static class MockQuoteProvider implements QuotesProvider {
        @NotNull
        @Override
        public String randomQuote() {
            return QUOTE;
        }
    }

    public static class MockAvatarProvider implements AvatarProvider {

        @NotNull
        @Override
        public String createForPerson(@NotNull Person person) {
            return AVATAR_URL;
        }
    }
}
