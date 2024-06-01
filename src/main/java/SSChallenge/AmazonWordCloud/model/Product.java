package SSChallenge.AmazonWordCloud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    private String productCode;
    private String productUrl;

}
