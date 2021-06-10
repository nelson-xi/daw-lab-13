package aplicaciones.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicaciones.spring.model.Producto;
import aplicaciones.spring.repository.ProductoRepository;

@Service("producto")
public class ProductoService {

	@Autowired
	ProductoRepository repo;

	public void guardar(Producto producto) {
		repo.save(producto);
	}

	public List<Producto> listar() {
		return repo.findAll();
	}

	public Producto buscar(Long id) {
		return repo.findById(id);
	}
	
	public Producto buscar(String nombre) {
		return repo.findByNombre(nombre);
	}

	public boolean eliminar(Long id) {
		try {
			repo.delete(repo.findById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
