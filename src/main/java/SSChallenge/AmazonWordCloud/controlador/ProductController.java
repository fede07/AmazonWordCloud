package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.event.Event;
import SSChallenge.AmazonWordCloud.model.Scrapper;
import SSChallenge.AmazonWordCloud.model.Product;
import SSChallenge.AmazonWordCloud.service.product.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class ProductController {

    private final ProductService productService;
    private final Scrapper scrapper;
    private final ApplicationEventPublisher eventPublisher;

    public ProductController(ProductService productService, Scrapper scrapper, ApplicationEventPublisher eventPublisher) {
        this.productService = productService;
        this.scrapper = scrapper;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Recibe un request POST con la direccion de un producto".
     * @param productUrl La direccion del producto
     */
    @PostMapping("api/products")
    public void postProductUrl(@RequestParam("productUrl") String productUrl) {
        String urlDecoded = URLDecoder.decode(productUrl, StandardCharsets.UTF_8);
        String productCode = urlDecoded.substring(urlDecoded.lastIndexOf("/") + 1);
        Product product = new Product();
        product.setProductUrl(urlDecoded);
        product.setProductCode(productCode);
        if (productService.findProductByCode(productCode) == null)
        {
            productService.saveProduct(product);
            scrapper.processLink(product.getProductUrl());
            eventPublisher.publishEvent(new Event(productCode));
        }
    }
}