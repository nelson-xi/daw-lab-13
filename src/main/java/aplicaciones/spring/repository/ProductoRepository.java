package aplicaciones.spring.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplicaciones.spring.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Serializable>{
	public abstract Producto findById(Long id);
	public abstract Producto findByNombre(String nombre);
}
