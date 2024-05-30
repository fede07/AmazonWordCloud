package SSChallenge.AmazonWordCloud.modelo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

@Component
public class Lector {
    public static void main(String[] args) {
        try {
            File archivo = new File("src/main/resources/stopWords/english.txt");
            Scanner leer = new Scanner(archivo);
            while (leer.hasNextLine()) {
                String word = leer.nextLine();
                System.out.println(word);
            }

        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
    }

    public HashSet<String> getStopWords() {
        HashSet<String> stopWords = new HashSet<>();
        try {
            File archivo = new File("src/main/resources/stopWords/english.txt");
            Scanner leer = new Scanner(archivo);
            while (leer.hasNextLine()) {
                String word = leer.nextLine();
                stopWords.add(word);
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo Stopwords no encontrado. No se filtraran palabras comunes.");
        }
        return stopWords;
    }

}
