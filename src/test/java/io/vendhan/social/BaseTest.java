package io.vendhan.social;

import io.vendhan.social.dao.FriendshipDao;
import org.junit.After;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest {
    @Autowired
    private FriendshipDao friendshipDao;

    @After
    public void tearDown() {
        try {
            clearDatabase();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void clearDatabase() throws Exception {
        friendshipDao.deleteAll();
    }
}
