package SSChallenge.AmazonWordCloud.component;

import SSChallenge.AmazonWordCloud.model.Word;
import SSChallenge.AmazonWordCloud.service.word.WordService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;

/**
 * Web Scrapper
 */
@Component
public class Scrapper {

    private final WordService wordService;
    private final HashSet<String> stopwords;

    public Scrapper(WordService wordService, Reader reader) {
        this.wordService = wordService;
        this.stopwords = reader.getStopWords();
        System.out.println("Scrapper Constructed-------------------------------------------------------");
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
            for (String word : palabras) {
                String wordClean = word.replaceAll("\\p{Punct}", "");
                wordClean = wordClean.replaceAll("\\s+", "");
                wordClean = wordClean.toLowerCase();
                System.out.println(wordClean);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procesa los links para obtener las descripciones de los productos.
     * @param link - URL del producto.
     */
    public void processLink(String link) {
        try {
            Element description = getDescription(link);
            if(description == null) return;
            saveWordCount(description);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la descripcion del producto.
     * @param link URL del producto
     * @return Descripcion del producto.
     * @throws IOException execute()
     */
    private static Element getDescription(String link) throws IOException {
        Connection.Response response = Jsoup.connect(link)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                .referrer("https://www.google.com")
                .followRedirects(true)
                .execute();
        Document document = response.parse();
        Element description = document.getElementById("productDescription");
        if(description == null) {
            System.out.println("Descripcion no encontrada");
            System.out.println(document.text());
        }
        return description;
    }

    /**
     * Guarda las palabras en la base de datos. Si existe aumenta la cantidad de apariciones.
     * @param description Descripcion a procesar.
     */
    private void saveWordCount(Element description) {
        description.getElementsByTag("h3").remove();
        String[] strings = description.text().split(" ");
        for(String word : strings) {
            String wordClean = word.replaceAll("\\p{Punct}", "");
            wordClean = wordClean.replaceAll("\\s+", "");
            wordClean = wordClean.toLowerCase();

            if(!stopwords.contains(wordClean)) {
                Word word1 = new Word(wordClean);
                wordService.saveWord(word1);
            }

        }

    }

}
