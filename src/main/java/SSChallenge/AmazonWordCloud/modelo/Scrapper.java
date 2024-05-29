package SSChallenge.AmazonWordCloud.modelo;

import SSChallenge.AmazonWordCloud.servicio.palabra.PalabraServicio;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
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
            Document document = Jsoup
                    .connect("https://www.amazon.com/gp/product/B00HUGXOAU")
//                    .proxy("127.0.0.1", 1080)
//                    .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
//                    .header("Content-Language", "en-US")
                    .get();
            //https://www.amazon.com/gp/product/B00HUGXOAU
            //https://www.amazon.com/gp/product/B00TRQPVKM
            Element description = document.getElementById("productDescription");
            if (description == null) {
                System.out.println(document.text());
                System.out.println("Product Description not found");
                return;
            }
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
            Document documento = Jsoup.connect(link).get();
            Element descripcion = documento.getElementById("productDescription");
            if(descripcion == null) {
                return;
            }
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
