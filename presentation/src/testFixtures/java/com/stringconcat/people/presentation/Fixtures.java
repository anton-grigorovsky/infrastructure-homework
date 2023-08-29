package com.stringconcat.people.presentation;

import com.stringconcat.people.businessPeople.Person;
import com.stringconcat.people.useCasePeople.CreatePerson;
import com.stringconcat.people.useCasePeople.GetPerson;
import com.stringconcat.people.useCasePeople.Me;
import com.stringconcat.people.useCasePeople.scenarios.PersonCreationSummary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static com.stringconcat.people.businessPeople.Fixtures.PERSON_ID;
import static com.stringconcat.people.businessPeople.Fixtures.getPerson;

public class Fixtures {
    public final static String PERSON_DETAILS_FORM = "<html>\n" +
            "  <head>\n" +
            "    <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <div class=\"header\">\n" +
            "      <h1>Create a new person</h1>\n" +
            "    </div>\n" +
            "    <div class=\"body\">\n" +
            "      <form action=\"/generate\" method=\"post\">\n" +
            "        <p>First name </p>\n" +
            "<input type=\"text\" name=\"firstName\">\n" +
            "        <p>Last name </p>\n" +
            "<input type=\"text\" name=\"secondName\">\n" +
            "        <p>Birthdate </p>\n" +
            "<input type=\"date\" name=\"birthDate\">\n" +
            "        <p>Gender </p>\n" +
            "<input type=\"text\" name=\"gender\">\n" +
            "        <p></p>\n" +
            "<input type=\"submit\"></form>\n" +
            "    </div>\n" +
            "  </body>\n" +
            "</html>\n";

    public final static String PERSON_DETAILED_VIEW = "<html>\n" +
            "  <head>\n" +
            "    <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <div class=\"header\">\n" +
            "      <h1><img src=\"https://url\" height=\"48\" width=\"48\"> John Wick</h1>\n" +
            "    </div>\n" +
            "    <div class=\"body\">\n" +
            "      <p>Birth date: 8 AUGUST 1988</p>\n" +
            "      <p>Favorite quote: Favorite quote</p>\n" +
            "    </div>\n" +
            "  </body>\n" +
            "</html>\n";


    public static class MockGetPerson implements GetPerson {
        @Nullable
        @Override
        public Person invoke(@NotNull UUID id) {
            return id.equals(PERSON_ID) ? getPerson() : null;
        }
    }

    public static class MockCreatePerson implements CreatePerson {
        @NotNull
        @Override
        public Person invoke(@NotNull PersonCreationSummary personInput) {
            return getPerson();
        }
    }

    public static class MockMe implements Me {
        @NotNull
        @Override
        public Person invoke() {
            return getPerson();
        }
    }

}
