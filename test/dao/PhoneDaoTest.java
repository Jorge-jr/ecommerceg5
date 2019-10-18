package dao;

import java.util.List;
import model.Phone;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PhoneDaoTest {

    public PhoneDaoTest() {
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

        UserDao userDao = new UserDao();
        PhoneDao phoneDao = new PhoneDao();

        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        Phone phoneSaved = phoneDao.save(new Phone(user, "27", "23456789"));

        assertNotNull(phoneSaved);
        assertNotNull(phoneSaved.getId());
    }

    @Test
    public void testGetByUser() {
        System.out.println("getByUser");

        UserDao userDao = new UserDao();
        PhoneDao phoneDao = new PhoneDao();

        User u1 = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        User u2 = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));

        Phone p1 = phoneDao.save(new Phone(u1, "27", "23456789"));
        Phone p2 = phoneDao.save(new Phone(u2, "27", "23456789"));

        List<Phone> phones = phoneDao.getByUser(u1);
        assertTrue(phones.contains(p1));
        assertFalse(phones.contains(p2));
    }

    @Test
    public void testUpdate() {
        System.out.println("update");

        UserDao userDao = new UserDao();
        PhoneDao phoneDao = new PhoneDao();

        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        Phone phoneSaved = phoneDao.save(new Phone(user, "27", "23456789"));

        phoneSaved.setDdd("28");
        Phone phoneUpdate = phoneDao.update(phoneSaved);
        assertNotNull(phoneUpdate);
        assertEquals(phoneSaved, phoneUpdate);
    }

    @Test
    public void testDeleteByAddressId() {
        System.out.println("deleteByAddressId");

        UserDao userDao = new UserDao();
        PhoneDao phoneDao = new PhoneDao();

        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        Phone phoneSaved = phoneDao.save(new Phone(user, "27", "23456789"));

        int result = phoneDao.deleteByAddressId(phoneSaved.getId());
        assertEquals(1, result);
        List<Phone> addresses = phoneDao.getByUser(user);
        assertTrue(addresses.isEmpty());
    }

}
