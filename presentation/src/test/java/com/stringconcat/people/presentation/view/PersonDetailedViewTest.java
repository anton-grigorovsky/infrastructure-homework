package com.stringconcat.people.presentation.view;

import com.stringconcat.people.presentation.model.PersonRespectfullViewModel;
import org.junit.jupiter.api.Test;

import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static com.stringconcat.people.presentation.Fixtures.PERSON_DETAILED_VIEW;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDetailedViewTest {

    @Test
    public void renderDetailedViewSuccess() {
        assertEquals(PERSON_DETAILED_VIEW,
                PersonDetailedViewKt.renderDetailedView(new PersonRespectfullViewModel(getPerson())));
    }
}
