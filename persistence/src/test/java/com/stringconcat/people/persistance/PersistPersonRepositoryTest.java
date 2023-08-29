package com.stringconcat.people.persistance;

import com.stringconcat.people.persistance.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersistPersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void persistPersonSuccess() {
        var person = getPerson();
        var persist = new PersistPersonToDataBase(repository);
        persist.persist(person);
        assertTrue(repository.existsById(person.getId()));
    }
}
