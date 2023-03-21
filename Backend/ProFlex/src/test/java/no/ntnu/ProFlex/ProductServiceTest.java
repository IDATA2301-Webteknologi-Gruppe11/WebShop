package no.ntnu.ProFlex;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import no.ntnu.ProFlex.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repository.ProductRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * This class represent the test class for ProductService
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@SpringJUnitConfig(ProductServiceTest.Config.class)
@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private static List<Product> products;

    @BeforeAll
    static void setUpBeforeAll() {
        products = new ArrayList<>();
        products.add(new Product("name1", 1, 20, "test1"));
        products.add(new Product("name2", 2, 21, "test2"));
    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(this.productRepository.findAll()).thenReturn(products);
        Iterable<Product> result = this.productService.getAll();
        assertEquals(products,result);
    }

    @Test
    void testFindById() {
        Product product = new Product("name1", 1, 20, "test1");
        when(this.productRepository.findById(1)).thenReturn(Optional.of(product));
        Product result = productService.findById(1);
        assertEquals(product, result);
    }

//    @Test
//    void testAdd() {
//        Product product = new Product("name3", 3, 20, "test3");
//        when(this.productRepository.save(product)).thenReturn(product);
//        boolean result = this.productService.add(product);
//        assertTrue(result);
//        assertEquals(3, products.size());
//        //products.remove(product);
//    }

    @Test
    void testAddWithInvalidProduct() {
        Product product = new Product("", 4, 21, "test3");
        boolean result = productService.add(product);
        assertFalse(result);
        verify(productRepository, never()).save(product);
    }

    @Test
    void testDelete() {
        when(productRepository.findById(1)).thenReturn(Optional.of(new Product("name1", 1, 20, "test1")));
        boolean result = productService.delete(1);
        assertTrue(result);
        verify(productRepository).deleteById(1);
    }

    @Test
    void testDeleteWithNonExistingProduct() {
        boolean result = productService.delete(1);
        assertFalse(result);
        verify(productRepository, never()).deleteById(anyInt());
    }


    static class Config{}

}
