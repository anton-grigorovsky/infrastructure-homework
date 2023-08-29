package com.stringconcat.people.presentation.view;

import org.junit.jupiter.api.Test;

import static com.stringconcat.people.presentation.Fixtures.PERSON_DETAILS_FORM;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDetailsFormTest {

    @Test
    public void createPersonDetailsFormSuccess() {
        assertEquals(PERSON_DETAILS_FORM, PersonDetailsFormKt.personDetailsForm());
    }
}
