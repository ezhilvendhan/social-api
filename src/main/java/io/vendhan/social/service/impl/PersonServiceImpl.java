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

    /**
     * Returns a Person entity from Email
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public Person getPersonFromEmail(String email) throws Exception {
        return personDao.findByEmail(email);
    }

}
