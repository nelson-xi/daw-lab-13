package aplicaciones.spring.controller;

import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import aplicaciones.spring.model.Producto;
import aplicaciones.spring.model.Venta;
import aplicaciones.spring.service.ClienteService;
import aplicaciones.spring.service.ProductoService;
import aplicaciones.spring.service.VentaService;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class VentaController {

	@Autowired
	@Qualifier("venta")
	VentaService ventaService;

	@Autowired
	@Qualifier("cliente")
	ClienteService clienteService;

	@Autowired
	@Qualifier("producto")
	ProductoService productoService;

	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Venta> ventas = ventaService.listar();
		model.addAttribute("ventas", ventas);
		model.addAttribute("titulo", "Lista de Ventas");
		return "ventaListar";
	}

	@RequestMapping("/form")
	public String formulario(Model model) {
		Venta venta = new Venta();
		model.addAttribute("venta", venta);
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("btn", "Registrar Venta");
		return "ventaForm";
	}

	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public String guardar(@Validated Venta venta, Model model) {
		try {
			Long id = venta.getCodProducto();
			Producto producto = productoService.buscar(id);

			if (venta.getCantidad() <= producto.getStock()) {
				int diferencia = producto.getStock() - venta.getCantidad();
				producto.setStock(diferencia);
				double subtotal = producto.getPrecio() * venta.getCantidad();
				double igv = subtotal * 0.18;
				double total = subtotal + igv;
				venta.setSubtotal(Precision.round(subtotal, 2));
				venta.setIgv(Precision.round(igv, 2));
				venta.setTotal(Precision.round(total, 2));
				productoService.guardar(producto);
				ventaService.guardar(venta);
			} else {
				model.addAttribute("ERROR",
						"No hay stock para este producto, solo tenemos un stock de: " + producto.getStock());
				venta = new Venta();
				model.addAttribute("venta", venta);
				model.addAttribute("productos", productoService.listar());
				model.addAttribute("clientes", clienteService.listar());
				model.addAttribute("btn", "Registrar Venta");
				return "ventaForm";
			}
		} catch (Exception e) {
		}
		return "redirect:/ventas/listar";
	}

	@RequestMapping("/form/{id}")
	public String actualizar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("venta", ventaService.buscar(id));
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("btn", "Actualiza Registro");
		return "ventaForm";
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		ventaService.eliminar(id);
		return "redirect:/ventas/listar";
	}

}
