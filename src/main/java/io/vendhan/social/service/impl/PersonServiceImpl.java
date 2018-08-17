package io.vendhan.social.service.impl;

import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPersonFromEmail(String email) {
        return null;
    }

}
