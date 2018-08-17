package io.vendhan.social.service;

import io.vendhan.social.dao.entity.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonService extends BaseService {

    Person getPersonFromEmail(String email);

}
