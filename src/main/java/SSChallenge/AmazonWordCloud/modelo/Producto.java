package SSChallenge.AmazonWordCloud.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    private String productCode;
    private String productUrl;

}
