package SSChallenge.AmazonWordCloud.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sitios")
@Data
public class Sitio {
    @Id
    private String productCode;
    private String productUrl;

//    public void setProductUrl(String productUrl) {
//        this.productUrl = decode(productUrl, StandardCharsets.UTF_8);
//        this.productCode = this.productUrl.substring(this.productUrl.lastIndexOf("/") + 1);
//    }

}
