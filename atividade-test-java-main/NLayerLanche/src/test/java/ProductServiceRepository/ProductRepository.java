import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        sampleProduct = new Product(1, "Product Test", 10.0, "test_image.jpg");
        productRepository.append(sampleProduct);
    }

    @Test
    void listarTodosOsProdutosDoRepositorio() {
        assertFalse(productRepository.getAll().isEmpty(), "A lista de produtos não deve estar vazia.");
    }

    @Test
    void obterProdutoPorIdValido() {
        Product foundProduct = productRepository.getById(1);
        assertNotNull(foundProduct, "Produto com ID válido deve ser encontrado.");
    }

    @Test
    void retornarNuloAoTentarObterProdutoPorIdInvalido() {
        Product foundProduct = productRepository.getById(999);
        assertNull(foundProduct, "Produto com ID inválido deve retornar nulo.");
    }

    @Test
    void verificarSeProdutoExistePorIdValido() {
        assertTrue(productRepository.exists(1), "Produto com ID válido deve existir.");
    }

    @Test
    void retornarFalsoAoVerificarExistenciaDeProdutoPorIdInvalido() {
        assertFalse(productRepository.exists(999), "Produto com ID inválido não deve existir.");
    }

    @Test
    void adicionarNovoProduto() {
        Product newProduct = new Product(2, "New Product", 15.0, "new_image.jpg");
        productRepository.append(newProduct);
        assertEquals(2, productRepository.getAll().size(), "O número de produtos deve ser 2 após a adição.");
    }

    @Test
    void removerProdutoExistente() {
        productRepository.remove(1);
        assertNull(productRepository.getById(1), "Produto deve ser removido com sucesso.");
    }

    @Test
    void naoRemoverProdutoComIdInexistente() {
        int initialSize = productRepository.getAll().size();
        productRepository.remove(999);
        assertEquals(initialSize, productRepository.getAll().size(), "Remover ID inexistente não deve alterar a lista de produtos.");
    }

    @Test
    void atualizarProdutoExistente() {
        Product updatedProduct = new Product(1, "Updated Product", 12.0, "updated_image.jpg");
        productRepository.update(1, updatedProduct);
        Product productInDb = productRepository.getById(1);
        assertEquals("Updated Product", productInDb.getDescription(), "O produto deve ser atualizado.");
    }
}
