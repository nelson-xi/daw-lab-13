package aplicaciones.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicaciones.spring.model.Venta;
import aplicaciones.spring.repository.VentaRepository;

@Service("venta")
public class VentaService {

	@Autowired
	VentaRepository repo;

	public void guardar(Venta venta) {
		repo.save(venta);
	}

	public List<Venta> listar() {
		return repo.findAll();
	}

	public Venta buscar(Long id) {
		return repo.findById(id);
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
