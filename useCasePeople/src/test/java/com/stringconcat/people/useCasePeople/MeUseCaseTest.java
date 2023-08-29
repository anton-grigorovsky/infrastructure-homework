package com.stringconcat.people.useCasePeople;

import com.stringconcat.people.useCasePeople.scenarios.MeUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MeUseCaseTest {

    @Test
    public void createMe() {
        var mockPersist = new Fixtures.MockPersonPersister();
        var meUseCase = new MeUseCase(mockPersist);
        var person = meUseCase.invoke();

        assertThat(person).
                usingRecursiveComparison().
                isEqualTo(mockPersist.get(person.getId()));
    }
}
