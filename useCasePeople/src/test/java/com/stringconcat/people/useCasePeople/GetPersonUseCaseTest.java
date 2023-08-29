package com.stringconcat.people.useCasePeople;

import com.stringconcat.people.businessPeople.Person;
import com.stringconcat.people.useCasePeople.scenarios.GetPersonUseCase;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetPersonUseCaseTest {

    @Test
    public void getPersonSuccess() {
        Fixtures.MockPersonExtractor getPerson = new Fixtures.MockPersonExtractor();
        GetPersonUseCase useCase = new GetPersonUseCase(getPerson);

        Person person = getPerson();
        getPerson.put(person.getId(), person);

        Person found = useCase.invoke(person.getId());
        assertThat(found).usingRecursiveComparison().isEqualTo(person);
    }

    @Test
    public void getPersonNotFound() {
        Fixtures.MockPersonExtractor getPerson = new Fixtures.MockPersonExtractor();
        GetPersonUseCase useCase = new GetPersonUseCase(getPerson);

        Person found = useCase.invoke(UUID.randomUUID());
        assertNull(found);
    }
}
