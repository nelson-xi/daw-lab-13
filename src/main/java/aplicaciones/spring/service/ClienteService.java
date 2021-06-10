package aplicaciones.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicaciones.spring.model.Cliente;
import aplicaciones.spring.repository.ClienteRepository;

@Service("cliente")
public class ClienteService {

	@Autowired
	ClienteRepository repo;

	public void guardar(Cliente cliente) {
		repo.save(cliente);
	}

	public List<Cliente> listar() {
		return repo.findAll();
	}

	public Cliente buscar(Long id) {
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
