package SSChallenge.AmazonWordCloud.model;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


/**
 * Lector de archivos.
 */
@Component
public class Reader {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/stopWords/english.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                System.out.println(word);
            }

        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
    }

    /**
     * Obtiene las stopwords del archivo "english.txt"
     * @return Hashset con las palabras mas comunes segun el archivo "english.txt".
     */
    public HashSet<String> getStopWords() {
        HashSet<String> stopWords = new HashSet<>();
        try {
            File file = new File("src/main/resources/stopWords/english.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                stopWords.add(word);
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo Stopwords no encontrado. No se filtraran palabras comunes.");
        }
        return stopWords;
    }

}
