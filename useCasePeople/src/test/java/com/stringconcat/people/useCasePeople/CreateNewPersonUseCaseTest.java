package com.stringconcat.people.useCasePeople;

import com.stringconcat.people.businessPeople.PersonGenerator;
import com.stringconcat.people.useCasePeople.scenarios.CreateNewPersonUseCase;
import com.stringconcat.people.useCasePeople.scenarios.PersonCreationSummary;
import org.junit.jupiter.api.Test;

import static com.stringconcat.people.businessPeople.Fixtures.BIRTH_DATE;
import static com.stringconcat.people.businessPeople.Fixtures.FIRST_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.SECOND_NAME;
import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.assertj.core.api.Assertions.assertThat;


public class CreateNewPersonUseCaseTest {

    @Test
    public void createPerson() {
        var command = new PersonCreationSummary(
                FIRST_NAME,
                SECOND_NAME,
                BIRTH_DATE.toString(),
                "male");
        var mockPersist = new Fixtures.MockPersonPersister();

        var useCase = new CreateNewPersonUseCase(
                mockPersist,
                new PersonGenerator(
                        new com.stringconcat.people.businessPeople.Fixtures.MockQuoteProvider(),
                        new com.stringconcat.people.businessPeople.Fixtures.MockAvatarProvider())
        );
        var person = useCase.invoke(command);
        assertThat(person)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(getPerson())
                .isEqualTo(mockPersist.get(person.getId()));
    }

}
