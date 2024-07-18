package data_access;

import entity.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserAccountDataAccessObjectTest {

    private InMemoryUserAccountDataAccessObject userDao;
    private UserAccount user1;
    private UserAccount user2;

    @BeforeEach
    void setUp() {
        userDao = new InMemoryUserAccountDataAccessObject();
        user1 = new UserAccount("user1", "pass1", "id1");
        user2 = new UserAccount("user2", "pass2", "id2");
        userDao.save(user1);
        userDao.save(user2);
    }

    @Test
    void testExistById() {
        assertTrue(userDao.existById("id1"));
        assertFalse(userDao.existById("nonexistent"));
    }

    @Test
    void testSaveAndGetById() {
        UserAccount user3 = new UserAccount("user3", "pass3", "id3");
        userDao.save(user3);
        assertEquals(user3, userDao.getById("id3"));
    }

    @Test
    void testDeleteById() {
        userDao.deleteById("id1");
        assertFalse(userDao.existById("id1"));
    }

    @Test
    void testGetAllUserAcc() {
        assertEquals(2, userDao.getAllUserAcc().size());
        assertTrue(userDao.getAllUserAcc().containsKey("id1"));
        assertTrue(userDao.getAllUserAcc().containsKey("id2"));
    }
}

