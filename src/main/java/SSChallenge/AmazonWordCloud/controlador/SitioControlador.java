package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.modelo.Sitio;
import SSChallenge.AmazonWordCloud.servicio.sitio.SitioServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class SitioControlador {

    private final SitioServicio sitioServicio;

    public SitioControlador(SitioServicio sitioServicio) {
        this.sitioServicio = sitioServicio;
    }

    @PostMapping("api/sitios")
    public void recibirProductUrl(@RequestParam("productUrl") String productUrl) {
        String urlDecodificada = URLDecoder.decode(productUrl, StandardCharsets.UTF_8);
        String productCode = urlDecodificada.substring(urlDecodificada.lastIndexOf("/") + 1);
        Sitio sitio = new Sitio();
        sitio.setProductUrl(urlDecodificada);
        sitio.setProductCode(productCode);
        sitioServicio.guardarSitio(sitio);
    }

}
