package SSChallenge.AmazonWordCloud.servicio.palabra;

import SSChallenge.AmazonWordCloud.modelo.Palabra;

import java.util.List;

public interface IPalabraServicio {
    Palabra buscarPalabra(String palabra);
    void guardarPalabra(Palabra palabra);
    List<Palabra> listarPalabras();
}
