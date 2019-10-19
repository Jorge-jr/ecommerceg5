package dao;

import model.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoDaoTest {

    @Test
    public void testSave() {
        System.out.println("save");
        Product product = new Product("name", "description", 2.54, "www.imagem.com/urldaimage");
        ProductDao productDao = new ProductDao();
        Product productSaved = productDao.save(product);
        assertNotNull(productSaved);
        assertNotNull(productSaved.getId());
    }

    @Test
    public void testGetByProductId() {
        System.out.println("getByProductId");
        Product product = new Product("name", "description", 2.54, "www.imagem.com/urldaimage");
        ProductDao productDao = new ProductDao();
        Product productSaved = productDao.save(product);
        Product productGet = productDao.getByProductId(productSaved.getId());
        assertEquals(productGet, productSaved);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Product product = new Product("name", "description", 2.54, "www.imagem.com/urldaimage");;
        ProductDao productDao = new ProductDao();
        Product productSaved = productDao.save(product);
        productSaved.setName("outro feijao");
        Product productUpdated = productDao.update(product);
        assertEquals(productSaved, productUpdated);
    }

    @Test
    public void testDeleteByProductId() {
        System.out.println("deleteByProductId");
        Product product = new Product("name", "description", 2.54, "www.imagem.com/urldaimage");
        ProductDao productDao = new ProductDao();
        Product productSaved = productDao.save(product);
        int result = productDao.deleteByProductId(productSaved.getId());
        assertEquals(1, result);
        Product productGet = productDao.getByProductId(productSaved.getId());
        assertNull(productGet);
    }

}
