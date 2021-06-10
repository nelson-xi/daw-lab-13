package aplicaciones.spring.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplicaciones.spring.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Serializable>{
	public abstract Cliente findById(Long id);
}
