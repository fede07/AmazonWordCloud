package SSChallenge.AmazonWordCloud.service.product;

import SSChallenge.AmazonWordCloud.model.Product;

public interface IProductService {
    void saveProduct(Product product);
    Product findProductByCode(String codigo);
}
