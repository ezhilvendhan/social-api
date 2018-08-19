package io.vendhan.social.dao.impl;

import io.vendhan.social.constant.ValidationConstant;
import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.exception.InvalidInputException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonDaoImpl extends PersonDao {

    public Person findByEmail(String email) {
        if(null == email) {
            return null;
        }
        List<Person> persons = this.getJpaRepository().findByEmail(email);
        if(null == persons || 0 == persons.size()) {
            throw new InvalidInputException(ValidationConstant.EMAIL);
        }
        return persons.get(0);
    }

    public List<Person> findByEmail(List<String> emails) throws Exception{
        if(null != emails) {
            return emails.stream()
                    .map(this::findByEmail)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
