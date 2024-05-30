package SSChallenge.AmazonWordCloud.repositorio;

import SSChallenge.AmazonWordCloud.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "productos", collectionResourceRel = "pro")
public interface ProductoRepositorio extends JpaRepository<Producto, String>{
}
