package SSChallenge.AmazonWordCloud.servicio.palabra;

import SSChallenge.AmazonWordCloud.modelo.Palabra;

public interface IPalabraServicio {
    Palabra buscarPalabra(String palabra);
    void guardarPalabra(Palabra palabra);
}
