package aplicaciones.spring.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplicaciones.spring.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Serializable>{
	public abstract Venta findById(Long id);
}

