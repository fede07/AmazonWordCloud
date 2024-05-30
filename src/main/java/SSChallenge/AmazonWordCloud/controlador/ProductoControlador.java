package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.evento.Evento;
import SSChallenge.AmazonWordCloud.modelo.Scrapper;
import SSChallenge.AmazonWordCloud.modelo.Producto;
import SSChallenge.AmazonWordCloud.servicio.producto.ProductoServicio;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class ProductoControlador {

    private final ProductoServicio productoServicio;
    private final Scrapper scrapper;
    private final ApplicationEventPublisher eventPublisher;

    public ProductoControlador(ProductoServicio productoServicio, Scrapper scrapper, ApplicationEventPublisher eventPublisher) {
        this.productoServicio = productoServicio;
        this.scrapper = scrapper;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("api/productos")
    public void recibirProductUrl(@RequestParam("productUrl") String productUrl) {
        String urlDecodificada = URLDecoder.decode(productUrl, StandardCharsets.UTF_8);
        String productCode = urlDecodificada.substring(urlDecodificada.lastIndexOf("/") + 1);
        Producto producto = new Producto();
        producto.setProductUrl(urlDecodificada);
        producto.setProductCode(productCode);
        if (productoServicio.buscarProductoPorCodigo(productCode) == null) {
            productoServicio.guardarProducto(producto);
            scrapper.procesarLink(producto.getProductUrl());
            eventPublisher.publishEvent(new Evento(productCode));
        }
    }
}
