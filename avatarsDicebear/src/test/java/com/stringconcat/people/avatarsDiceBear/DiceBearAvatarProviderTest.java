package com.stringconcat.people.avatarsDiceBear;

import com.stringconcat.people.businessPeople.Person;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.stringconcat.people.businessPeople.Fixtures.getPerson;
import static org.assertj.core.api.Assertions.assertThat;

public class DiceBearAvatarProviderTest {

    @Test
    public void createForPersonSuccess() throws MalformedURLException {
        var person = getPerson();
        var avatarProvider = new DiceBearAvatarProvider();
        var avatarUrl = avatarProvider.createForPerson(person);
        assertThat(new URL(avatarUrl))
                .hasHost("avatars.dicebear.com")
                .hasProtocol("https")
                .hasPath("/v2/" +
                        (person.getSex() == Person.Sex.MAN ? "male" : "female") +
                        "/" + person.getFirstName() + person.getSecondName() + ".svg");
    }
}
