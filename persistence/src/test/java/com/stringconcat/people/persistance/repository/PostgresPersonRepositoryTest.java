package com.stringconcat.people.persistance.repository;

import com.stringconcat.people.persistance.TestPostgresContainer;
import com.stringconcat.people.persistance.model.PersonEntity;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.stringconcat.people.businessPeople.Fixtures.PERSON_ID;
import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresPersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void insertAndSelectPerson() {
        var personEntity = PersonEntity.Companion.fromBusiness(getPerson());
        repository.save(personEntity);
        var extractedPerson = repository.findById(personEntity.getId());
        assertTrue(extractedPerson.isPresent());
        assertThat(personEntity)
                .usingRecursiveComparison()
                .isEqualTo(extractedPerson.get());
    }

    @Test
    public void personNotFound() {
        var extractedPerson = repository.findById(PERSON_ID);
        assertFalse(extractedPerson.isPresent());
    }

    @ClassRule
    public static TestPostgresContainer postgres = TestPostgresContainer
            .getInstance();
}
