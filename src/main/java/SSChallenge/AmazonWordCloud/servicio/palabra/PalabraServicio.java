package SSChallenge.AmazonWordCloud.servicio.palabra;

import SSChallenge.AmazonWordCloud.modelo.Palabra;
import SSChallenge.AmazonWordCloud.repositorio.PalabraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PalabraServicio implements IPalabraServicio{

    @Autowired
    private PalabraRepositorio palabraRepositorio;

    @Override
    public Palabra buscarPalabra(String palabra) {
        return palabraRepositorio.findById(palabra).orElse(null);
    }

    @Override
    public void guardarPalabra(Palabra palabra) {
        Palabra palabraExistente = buscarPalabra(palabra.getPalabra());
        if(palabraExistente != null){
            int apariciones = palabraExistente.getApariciones();
            palabraExistente.setApariciones(apariciones+1);
            palabraRepositorio.save(palabraExistente);
        }else {
            palabraRepositorio.save(palabra);
        }
    }
}
