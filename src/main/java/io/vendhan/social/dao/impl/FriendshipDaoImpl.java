package io.vendhan.social.dao.impl;

import io.vendhan.social.dao.FriendshipDao;
import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.entity.FriendshipId;
import io.vendhan.social.dao.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendshipDaoImpl extends FriendshipDao {

    @Autowired
    private PersonDao personDao;

    public Friendship getByEmails(String emailOne, String emailTwo) throws Exception{
        Person personOne = personDao.findByEmail(emailOne);
        Person personTwo = personDao.findByEmail(emailTwo);
        return this.getJpaRepository().getOne(
                new FriendshipId(personOne.getId(), personTwo.getId()));
    }
}
