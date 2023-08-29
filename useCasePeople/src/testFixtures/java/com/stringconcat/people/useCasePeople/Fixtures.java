package com.stringconcat.people.useCasePeople;

import com.stringconcat.people.businessPeople.Person;
import com.stringconcat.people.useCasePeople.access.PersonExtractor;
import com.stringconcat.people.useCasePeople.access.PersonPersister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class Fixtures {

    public static class MockPersonExtractor extends HashMap<UUID, Person> implements PersonExtractor {
        @Nullable
        @Override
        public Person getById(@NotNull UUID id) {
            return get(id);
        }
    }

    public static class MockPersonPersister extends HashMap<UUID, Person> implements PersonPersister {
        @Override
        public void persist(@NotNull Person person) {
            this.put(person.getId(), person);
        }
    }
}
