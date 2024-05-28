package SSChallenge.AmazonWordCloud.servicio.sitio;

import SSChallenge.AmazonWordCloud.modelo.Sitio;

public interface ISitioServicio {
    void guardarSitio(Sitio sitio);
    @SuppressWarnings("unused")
    Sitio buscarSitioPorCodigo(String codigo);
}
