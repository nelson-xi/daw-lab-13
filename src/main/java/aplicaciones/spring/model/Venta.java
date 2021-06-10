package aplicaciones.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "VENTAS")
@Getter @Setter @NoArgsConstructor @ToString
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@NotNull
	@Column(name = "codigo_cliente")
	private Long codCliente;
	@NotNull
	@Column(name = "codigo_producto")
	private Long codProducto;
	@NotNull
	private Integer cantidad;
	private Double subtotal;
	private Double igv;
	private Double total;
	
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	
}
