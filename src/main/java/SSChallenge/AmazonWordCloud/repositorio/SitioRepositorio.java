package SSChallenge.AmazonWordCloud.repositorio;

import SSChallenge.AmazonWordCloud.modelo.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "sitios", collectionResourceRel = "sit")
public interface SitioRepositorio extends JpaRepository<Sitio, String>{
}
