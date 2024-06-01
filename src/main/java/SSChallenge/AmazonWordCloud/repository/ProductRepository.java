package SSChallenge.AmazonWordCloud.repository;

import SSChallenge.AmazonWordCloud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "products", collectionResourceRel = "pro")
public interface ProductRepository extends JpaRepository<Product, String>{
}
