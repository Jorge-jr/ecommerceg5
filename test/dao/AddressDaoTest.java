package dao;

import java.util.List;
import model.Address;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressDaoTest {

    public AddressDaoTest() {
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
        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        AddressDao addressDao = new AddressDao();
        Address addressSaved = addressDao.save(new Address(user, "rua tal tal", "47b", "ingá", "27500000", "rio de janeiro", "RJ"));
        assertNotNull(addressSaved);
        assertNotNull(addressSaved.getId());
    }

    @Test
    public void testGetByUser() {
        System.out.println("getByUser");

        UserDao userDao = new UserDao();
        AddressDao addressDao = new AddressDao();

        User u1 = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        User u2 = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));

        Address a1 = addressDao.save(new Address(u1, "rua tal tal", "47b", "ingá", "27500000", "rio de janeiro", "RJ"));
        Address a2 = addressDao.save(new Address(u2, "rua tal tal", "47b", "ingá", "27500000", "rio de janeiro", "RJ"));
        List<Address> addresses = addressDao.getByUser(u1);
        assertTrue(addresses.contains(a1));
        assertFalse(addresses.contains(a2));
    }

    @Test
    public void testUpdate() {
        System.out.println("update");

        UserDao userDao = new UserDao();
        AddressDao addressDao = new AddressDao();

        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        Address addressSaved = addressDao.save(new Address(user, "rua tal tal", "47b", "ingá", "27500000", "rio de janeiro", "RJ"));

        addressSaved.setStreet("Rua Abacate de Oliveira");
        Address addressUpdated = addressDao.update(addressSaved);
        assertNotNull(addressUpdated);
        assertEquals(addressSaved, addressUpdated);
    }

    @Test
    public void testDeleteByAddressId() {
        System.out.println("deleteByAddressId");

        UserDao userDao = new UserDao();
        AddressDao addressDao = new AddressDao();

        User user = userDao.save(new User("11749803704", "eduardo@gmail.com", "Eduardo Lagoeiro", "eduardolagoeiro", "123456"));
        Address addressSaved = addressDao.save(new Address(user, "rua tal tal", "47b", "ingá", "27500000", "rio de janeiro", "RJ"));

        int result = addressDao.deleteByAddressId(addressSaved.getId());
        assertEquals(1, result);
        List<Address> addresses = addressDao.getByUser(user);
        assertTrue(addresses.isEmpty());
    }

}
