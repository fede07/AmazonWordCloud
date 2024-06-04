package SSChallenge.AmazonWordCloud.service.product;

import SSChallenge.AmazonWordCloud.model.Product;

import java.util.List;

public interface IProductService {
    void saveProduct(Product product);
    @SuppressWarnings("unused")
    Product findProductByCode(String codigo);
    List<Product> findAllProducts();
}
