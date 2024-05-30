package SSChallenge.AmazonWordCloud.servicio.producto;

import SSChallenge.AmazonWordCloud.modelo.Producto;

public interface IProductoServicio {
    void guardarProducto(Producto producto);
    Producto buscarProductoPorCodigo(String codigo);
}
