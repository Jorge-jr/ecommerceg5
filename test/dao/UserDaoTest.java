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

//    @Test
//    public void testSave() {
//        System.out.println("save");
//        User user = null;
//        UserDao instance = new UserDao();
//        User expResult = null;
//        User result = instance.save(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testGetByUserId() {
        System.out.println("getByUserId");
        User user = new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456");
        UserDao userDao = new UserDao();
        User userSaved = userDao.save(user);
        Long id = userSaved.getId();
        User userResult = userDao.getByUserId(id);
        assertNotNull(userResult);
        assertEquals(user.getCpf(), userResult.getCpf());
    }
    
}
