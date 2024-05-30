package SSChallenge.AmazonWordCloud.servicio.sitio;

import SSChallenge.AmazonWordCloud.modelo.Producto;

public interface IProductoServicio {
    void guardarProducto(Producto producto);
    Producto buscarProductoPorCodigo(String codigo);
}
