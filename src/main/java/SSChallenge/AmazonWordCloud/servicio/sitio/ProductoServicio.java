package SSChallenge.AmazonWordCloud.servicio.sitio;

import SSChallenge.AmazonWordCloud.modelo.Producto;
import SSChallenge.AmazonWordCloud.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public void guardarProducto(Producto producto) {
        productoRepositorio.save(producto);
    }

    @Override
    public Producto buscarProductoPorCodigo(String codigo) {
        return productoRepositorio.findById(codigo).orElse(null);
    }
}
