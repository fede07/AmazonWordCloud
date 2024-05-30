package SSChallenge.AmazonWordCloud.modelo;

import SSChallenge.AmazonWordCloud.servicio.palabra.PalabraServicio;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;

@Component
public class Scrapper {

    private final PalabraServicio palabraServicio;
    private final HashSet<String> stopwords;

    public Scrapper(PalabraServicio palabraServicio, Lector lector) {
        this.palabraServicio = palabraServicio;
        this.stopwords = lector.getStopWords();
        System.out.println("Scrapper started-------------------------------------------------------");
    }

    public static void main(String[] args) {
        try {
            Connection.Response response = Jsoup.connect("https://www.amazon.com/gp/product/B00TRQPVKM")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                    .referrer("https://www.google.com")
                    .followRedirects(true)
                    .execute();
            Document document = response.parse();
            Element description = document.getElementById("productDescription");
            if (description == null) {
                System.out.println(document.text());
                System.out.println("Product Description not found");
                return;
            }
            description.getElementsByTag("h3").remove();
            System.out.println("Product Description: ");
            System.out.println(description.text());
            String[] palabras = description.text().split(" ");
            for (String palabra : palabras) {
                String palabraLimpia = palabra.replaceAll("\\p{Punct}", "");
                palabraLimpia = palabraLimpia.replaceAll("\\s+", "");
                palabraLimpia = palabraLimpia.toLowerCase();
                System.out.println(palabraLimpia);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void procesarLink(String link) {
        try {
            Connection.Response response = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                    .referrer("https://www.google.com")
                    .followRedirects(true)
                    .execute();
            Document documento = response.parse();
            Element descripcion = documento.getElementById("productDescription");
            if(descripcion == null) {
                System.out.println("Descripcion no encontrada");
                System.out.println(documento.text());
                return;
            }
            descripcion.getElementsByTag("h3").remove();
            String[] palabras = descripcion.text().split(" ");
            for(String palabra : palabras) {
                String palabraLimpia = palabra.replaceAll("\\p{Punct}", "");
                palabraLimpia = palabraLimpia.replaceAll("\\s+", "");
                palabraLimpia = palabraLimpia.toLowerCase();

                if(!stopwords.contains(palabraLimpia)) {
                    Palabra palabra1 = new Palabra(palabraLimpia);
                    palabraServicio.guardarPalabra(palabra1);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
