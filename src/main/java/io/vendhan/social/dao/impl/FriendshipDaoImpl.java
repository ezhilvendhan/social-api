package io.vendhan.social.dao.impl;

import io.vendhan.social.dao.FriendshipDao;
import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.constant.QueryConstant;
import io.vendhan.social.dao.entity.Friendship;
import io.vendhan.social.dao.entity.FriendshipId;
import io.vendhan.social.dao.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class FriendshipDaoImpl extends FriendshipDao {

    @Autowired
    private PersonDao personDao;

    @Override
    public Optional<Friendship> getByEmails(
            String emailOne, String emailTwo) throws Exception{
        Person personOne = personDao.findByEmail(emailOne);
        Person personTwo = personDao.findByEmail(emailTwo);
        return this.getJpaRepository().findById(
                new FriendshipId(personOne.getId(), personTwo.getId()));
    }

    @Override
    public List<Friendship> getFriendsByEmail(String email) throws Exception {
        Person personOne = personDao.findByEmail(email);
        return this.getJpaRepository().findByFriendOneId(personOne.getId());
    }

    @Override
    public List<String> getCommonFriendsByEmail(
            String emailOne, String emailTwo) throws Exception {
        Query query = entityManager.createNativeQuery(
                QueryConstant.GET_COMMON_FRIENDS);
        query.setParameter("emailone", emailOne);
        query.setParameter("emailtwo", emailTwo);

        return ((List<String>)query.getResultList());
    }
}
