package com.stringconcat.people.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stringconcat.people.presentation.Fixtures;
import com.stringconcat.people.useCasePeople.CreatePerson;
import com.stringconcat.people.useCasePeople.GetPerson;
import com.stringconcat.people.useCasePeople.Me;
import com.stringconcat.people.useCasePeople.scenarios.PersonCreationSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.stringconcat.people.businessPeople.Fixtures.BIRTH_DATE;
import static com.stringconcat.people.businessPeople.Fixtures.FIRST_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.PERSON_ID;
import static com.stringconcat.people.businessPeople.Fixtures.SECOND_NAME;
import static com.stringconcat.people.presentation.EndpointURLKt.API_ME;
import static com.stringconcat.people.presentation.EndpointURLKt.API_PERSON_GENERATE;
import static com.stringconcat.people.presentation.EndpointURLKt.API_PERSON_GET_BY_ID;
import static com.stringconcat.people.presentation.Fixtures.PERSON_DETAILED_VIEW;
import static com.stringconcat.people.presentation.Fixtures.PERSON_DETAILS_FORM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = PeopleControllerTest.TestConfiguration.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PeopleController controller;

    @Test
    public void getPersonSuccess() throws Exception {
        mvc.perform(get(API_PERSON_GET_BY_ID.replace("{id}", PERSON_ID.toString())))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(PERSON_DETAILED_VIEW));
    }

    @Test
    public void getPersonNotFound() throws Exception {
        mvc.perform(get(API_PERSON_GET_BY_ID.replace("{id}", UUID.randomUUID().toString())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMeSuccess() throws Exception {
        mvc.perform(get(API_ME))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(PERSON_DETAILED_VIEW));
    }

    @Test
    public void showCreationFormSuccess() throws Exception {
        mvc.perform(get(API_PERSON_GENERATE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(PERSON_DETAILS_FORM));
    }

    @Test
    public void createPersonSuccess() throws Exception {
        var requestBody = new PersonCreationSummary(FIRST_NAME, SECOND_NAME, BIRTH_DATE.toString(), "male");
        mvc.perform(post(API_PERSON_GENERATE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(redirectedUrl("/id/" + PERSON_ID))
                .andExpect(status().isFound());
    }

    @Configuration
    public static class TestConfiguration {
        @Bean
        public PeopleController controller(GetPerson getPerson,
                                           CreatePerson createPerson,
                                           Me me) {
            return new PeopleController(getPerson, createPerson, me);
        }

        @Bean
        public GetPerson getPerson() {
            return new Fixtures.MockGetPerson();
        }

        @Bean
        public Me me() {
            return new Fixtures.MockMe();
        }

        @Bean
        public CreatePerson createPerson() {
            return new Fixtures.MockCreatePerson();
        }
    }
}
