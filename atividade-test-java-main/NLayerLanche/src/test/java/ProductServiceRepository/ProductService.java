import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);
        productService.addProduct(new Product(1, "Product Test", 10.0, "test_image.jpg"));
    }

    @Test
    void realizarVendaDeProduto() {
        boolean vendaRealizada = productService.sellProduct(1, 2);
        assertTrue(vendaRealizada, "Venda de produto deve ser realizada com sucesso.");
        assertEquals(8, productService.getProductById(1).getStock(), "Estoque deve ser atualizado após a venda.");
    }
}
