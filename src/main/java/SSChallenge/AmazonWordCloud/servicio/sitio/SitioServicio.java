package SSChallenge.AmazonWordCloud.servicio.sitio;

import SSChallenge.AmazonWordCloud.modelo.Sitio;
import SSChallenge.AmazonWordCloud.repositorio.SitioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SitioServicio implements ISitioServicio{

    @Autowired
    private SitioRepositorio sitioRepositorio;

    @Override
    public void guardarSitio(Sitio sitio) {
        sitioRepositorio.save(sitio);
    }

    @Override
    public Sitio buscarSitioPorCodigo(String codigo) {
        return sitioRepositorio.findByProductCode(codigo);
    }
}
