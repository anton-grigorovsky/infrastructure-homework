package com.stringconcat.people.e2e.cases;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static com.stringconcat.people.businessPeople.Fixtures.BIRTH_DATE;
import static com.stringconcat.people.businessPeople.Fixtures.FIRST_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.SECOND_NAME;
import static com.stringconcat.people.e2e.steps.PersonSteps.createPerson;
import static com.stringconcat.people.e2e.steps.PersonSteps.getForm;
import static com.stringconcat.people.e2e.steps.PersonSteps.getPersonByUrl;

@Epic("Create a person")
public class CreatePersonCase {

    @Test
    @Story("Create a person")
    public void createPersonTest() {
        RestAssured.baseURI = "http://localhost:8080";

        getForm("/generate");
        var id = createPerson("/generate?firstName=" + FIRST_NAME +
                "&secondName=" + SECOND_NAME +
                "&birthDate=" + BIRTH_DATE + "&gender=male");
        getPersonByUrl("/id/" + id);
    }
}
