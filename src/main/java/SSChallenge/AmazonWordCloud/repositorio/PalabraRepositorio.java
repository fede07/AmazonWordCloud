package SSChallenge.AmazonWordCloud.repositorio;

import SSChallenge.AmazonWordCloud.modelo.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabraRepositorio extends JpaRepository<Palabra, String> {
}
