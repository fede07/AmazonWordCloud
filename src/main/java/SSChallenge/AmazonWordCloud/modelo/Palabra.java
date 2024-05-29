package SSChallenge.AmazonWordCloud.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "palabras")
@Data
@NoArgsConstructor
public class Palabra {
    @Id
    String palabra;
    int apariciones;

    public Palabra(String palabra) {
        this.palabra = palabra;
        this.apariciones = 1;
    }
}
