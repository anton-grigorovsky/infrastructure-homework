package com.stringconcat.people.persistance;

import com.stringconcat.people.businessPeople.Person;
import com.stringconcat.people.persistance.model.PersonEntity;
import com.stringconcat.people.persistance.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.stringconcat.people.businessPeople.Fixtures.PERSON_ID;
import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonExtractorFromRepositoryTest {
    @Autowired
    private PersonRepository repository;

    @Test
    public void extractPersonSuccess() {
        var person = getPerson();
        insertPerson(person);
        var extractor = new PersonExtractorFromRepository(repository);
        var extracted = extractor.getById(PERSON_ID);
        assertNotNull(extracted);
        assertThat(person).usingRecursiveComparison().isEqualTo(extracted);
        clearDb();
    }

    @Test
    public void extractPersonNotFound() {
        var extractor = new PersonExtractorFromRepository(repository);
        var extracted = extractor.getById(PERSON_ID);
        assertNull(extracted);
    }

    private void clearDb() {
        repository.deleteAll();
    }

    private void insertPerson(Person person) {
        var personEntity = PersonEntity.Companion.fromBusiness(person);
        repository.save(personEntity);
    }
}
