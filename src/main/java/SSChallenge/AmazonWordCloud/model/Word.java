package SSChallenge.AmazonWordCloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
public class Word {
    @Id
    String word;
    int occurences;

    public Word(String word) {
        this.word = word;
        this.occurences = 1;
    }
}
