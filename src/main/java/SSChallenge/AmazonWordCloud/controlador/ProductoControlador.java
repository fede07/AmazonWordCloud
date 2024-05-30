package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.modelo.Scrapper;
import SSChallenge.AmazonWordCloud.modelo.Producto;
import SSChallenge.AmazonWordCloud.servicio.sitio.ProductoServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class ProductoControlador {

    private final ProductoServicio productoServicio;
    private final Scrapper scrapper;

    public ProductoControlador(ProductoServicio productoServicio, Scrapper scrapper) {
        this.productoServicio = productoServicio;
        this.scrapper = scrapper;

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
        }


    }
}
