package aplicaciones.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CLIENTES")
@Getter @Setter @NoArgsConstructor @ToString
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nombres;
	@NotNull
	private String apellidos;
	@NotNull
	private String dni;
	@NotNull
	private String telefono;
	@NotNull
	private String direccion;
	
}
