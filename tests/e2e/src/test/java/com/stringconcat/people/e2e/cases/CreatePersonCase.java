package com.stringconcat.people.e2e.cases;

import com.stringconcat.people.common.StandConfiguration;
import com.stringconcat.people.common.StandContainer;
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

    private final StandConfiguration settings = new StandConfiguration();
    private final StandContainer standContainer = new StandContainer(settings);

    @Test
    @Story("Create a person")
    public void createPersonTest() {
        standContainer.start();
        RestAssured.baseURI = settings.peopleBaseUrl;

        getForm("/generate");
        var id = createPerson("/generate?firstName=" + FIRST_NAME +
                "&secondName=" + SECOND_NAME +
                "&birthDate=" + BIRTH_DATE + "&gender=male");
        getPersonByUrl("/id/" + id);
        standContainer.stop();
    }
}
