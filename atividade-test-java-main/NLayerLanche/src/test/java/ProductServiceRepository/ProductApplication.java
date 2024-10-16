import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductApplicationTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(new ProductRepository());
        productService.addProduct(new Product(1, "Product Test", 10.0, "test_image.jpg"));
    }

    @Test
    void listarTodosOsProdutosIntegrado() {
        assertFalse(productService.listAllProducts().isEmpty(), "Deve retornar lista de produtos.");
    }

    @Test
    void obterProdutoPorIdValidoIntegrado() {
        Product foundProduct = productService.getProductById(1);
        assertNotNull(foundProduct, "Produto válido deve ser encontrado.");
    }

    @Test
    void tratarErroAoObterProdutoPorIdInvalidoIntegrado() {
        assertNull(productService.getProductById(999), "Produto inválido deve retornar nulo.");
    }

    @Test
    void adicionarNovoProdutoIntegrado() {
        Product newProduct = new Product(2, "New Product", 20.0, "new_image.jpg");
        productService.addProduct(newProduct);
        assertEquals(2, productService.listAllProducts().size(), "Produto novo deve ser adicionado.");
    }
}
