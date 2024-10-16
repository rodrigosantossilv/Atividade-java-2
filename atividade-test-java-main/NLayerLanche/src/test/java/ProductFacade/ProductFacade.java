import com.snack.entities.Product;
import com.snack.applications.ProductApplication;
import com.snack.facade.ProductFacade;
import com.snack.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductFacadeTest {

    private ProductRepository productRepository;
    private ProductApplication productApplication;
    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
        // Inicializa o repositório, aplicação e fachada para um ambiente real de integração
        productRepository = new ProductRepository();
        productApplication = new ProductApplication(productRepository);
        productFacade = new ProductFacade(productApplication);

        // Limpa e adiciona um produto de teste antes de cada execução de teste
        productRepository.getAll().clear();
        Product testProduct = new Product(1, "Product Test", 10.0f, "test_image.jpg");
        productFacade.append(testProduct);
    }

    @Test
    void retornarListaCompletaDeProdutos() {
        List<Product> result = productFacade.getAll();

        assertEquals(1, result.size(), "A lista deve conter um produto.");
        assertEquals("Product Test", result.get(0).getDescription(), "A descrição do produto deve ser 'Product Test'.");
    }

    @Test
    void retornarProdutoCorretoPorIdValido() {
        Product result = productFacade.getById(1);

        assertNotNull(result, "O produto não deve ser nulo para um ID válido.");
        assertEquals(1, result.getId(), "O ID do produto retornado deve ser 1.");
    }

    @Test
    void retornarTrueParaIdExistenteEFalseParaIdInexistente() {
        assertTrue(productFacade.exists(1), "Deve retornar true para um ID existente.");
        assertFalse(productFacade.exists(999), "Deve retornar false para um ID inexistente.");
    }

    @Test
    void adicionarNovoProdutoCorretamente() {
        Product newProduct = new Product(2, "Product New", 15.0f, "new_image.jpg");
        productFacade.append(newProduct);

        assertEquals(2, productFacade.getAll().size(), "A lista deve conter dois produtos após adicionar um novo.");
        assertEquals("Product New", productFacade.getById(2).getDescription(), "O segundo produto deve ter a descrição 'Product New'.");
    }

    @Test
    void removerProdutoExistentePorIdValido() {
        productFacade.remove(1);

        assertEquals(0, productFacade.getAll().size(), "A lista deve estar vazia após remover o único produto.");
    }
}
