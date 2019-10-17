package dao;

import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSave() {
        System.out.println("save");
        User user = new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456");
        UserDao userDao = new UserDao();
        User userSaved = userDao.save(user);
        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
    }

    @Test
    public void testGetByUserId() {
        System.out.println("getByUserId");
        User user = new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456");
        UserDao userDao = new UserDao();
        User userSaved = userDao.save(user);
        Long id = userSaved.getId();
        User userResult = userDao.getByUserId(id);
        assertNotNull(userResult);
        assertEquals(user, userResult);
    }
    
    @Test
    public void update() {
        System.out.println("update");
        User user = new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456");
        UserDao userDao = new UserDao();
        User userSaved = userDao.save(user);
        userSaved.setFullName("Abacate de Oliveira");
        User userResult = userDao.update(userSaved);
        assertNotNull(userResult);
        assertEquals(user, userResult);
    }
    
}
