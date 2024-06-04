package SSChallenge.AmazonWordCloud.service.product;

import SSChallenge.AmazonWordCloud.model.Product;
import SSChallenge.AmazonWordCloud.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Guarda un producto en la base de datos.
     * @param product Producto a guardar.
     */
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Busca priductos en la base de datos por codigo de producto
     * @param code Codigo de producto a buscar
     * @return Producto encontrado o NULL si no lo encontro
     */
    @Override
    public Product findProductByCode(String code) {
        return productRepository.findById(code).orElse(null);
    }

    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
