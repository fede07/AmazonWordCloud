package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.modelo.Scrapper;
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
    private final Scrapper scrapper;

    public SitioControlador(SitioServicio sitioServicio, Scrapper scrapper) {
        this.sitioServicio = sitioServicio;
        this.scrapper = scrapper;

    }

    @PostMapping("api/sitios")
    public void recibirProductUrl(@RequestParam("productUrl") String productUrl) {
        String urlDecodificada = URLDecoder.decode(productUrl, StandardCharsets.UTF_8);
        String productCode = urlDecodificada.substring(urlDecodificada.lastIndexOf("/") + 1);
        Sitio sitio = new Sitio();
        sitio.setProductUrl(urlDecodificada);
        sitio.setProductCode(productCode);
        if (sitioServicio.buscarSitioPorCodigo(productCode) == null) {
            sitioServicio.guardarSitio(sitio);
            scrapper.procesarLink(sitio.getProductUrl());
        }


    }
}
