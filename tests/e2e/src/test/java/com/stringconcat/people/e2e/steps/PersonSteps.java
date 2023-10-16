package com.stringconcat.people.e2e.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.regex.Pattern;

import static com.stringconcat.people.presentation.Fixtures.PERSON_DETAILS_FORM;


public class PersonSteps {

    private final static Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    @Step
    public static void getForm(String url) {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(url);
        Assertions.assertEquals(response.getStatusCode(), 200);
        Assertions.assertEquals(response.body().print(), PERSON_DETAILS_FORM);
    }

    @Step
    public static String createPerson(String url) {
        RequestSpecification request = RestAssured.given();
        Response response = request.post(url);
        Assertions.assertEquals(response.getStatusCode(), 302);
        var location = response.headers().get("Location").getValue();
        var matcher = UUID_PATTERN.matcher(location);
        Assertions.assertTrue(matcher.find());
        return matcher.group(0);
    }

    @Step
    public static void getPersonByUrl(String url) {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(url);
        Assertions.assertEquals(response.getStatusCode(), 200);
    }
}
