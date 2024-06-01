package SSChallenge.AmazonWordCloud.repository;

import SSChallenge.AmazonWordCloud.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, String> {
}
