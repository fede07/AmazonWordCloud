package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.servicio.scraper.ScraperServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class ScraperControlador {
    @Autowired
    ScraperServicio scraperServicio;

}