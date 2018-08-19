package io.vendhan.social;

import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.StatusDao;
import io.vendhan.social.dao.constant.StatusEnum;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Status;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private StatusDao statusDao;

    @Before
    public void setup() {
        if(personDao.findAll().isEmpty()) {
            saveAndFlushPerson("andy@example.com", "Andy", "A");
            saveAndFlushPerson("john@example.com", "John", "J");
            saveAndFlushPerson("user1@example.com", "User", "1");
            saveAndFlushPerson("lisa@example.com", "Lisa", "L");
            saveAndFlushPerson("kate@example.com", "Kate", "K");
            saveAndFlushPerson("user2@example.com", "User", "2");
            saveAndFlushPerson("user3@example.com", "User", "3");
        }
        if(statusDao.findAll().isEmpty()) {
            saveAndFlushStatus(StatusEnum.ACTIVE);
            saveAndFlushStatus(StatusEnum.INACTIVE);
            saveAndFlushStatus(StatusEnum.BLOCKED);
        }
    }

    private void saveAndFlushPerson(
            String email, String firstName, String lastName) {
        Person person = new Person(email, firstName, lastName);
        personDao.getJpaRepository().saveAndFlush(person);
    }

    private void saveAndFlushStatus(StatusEnum type) {
        Status status = new Status(type.getLabel());
        statusDao.getJpaRepository().saveAndFlush(status);
    }
}
