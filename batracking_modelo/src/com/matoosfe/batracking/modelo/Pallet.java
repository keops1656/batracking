package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa la tabla Pallet
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 
 * 18 ago. 2017- 00:05:06<br>
 * <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking" target="_top">Soporte</a><br>
 * <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Entity
@Table(name = "pallet")
@NamedQuery(name = "Pallet.findAll", query = "SELECT p FROM Pallet p")
public class Pallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pallet")
	private int idPallet;

	@Column(name = "pall_codigo")
	private String pallCodigo;

	@Column(name = "pall_descripcion")
	private String pallDescripcion;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "pallet")
	private List<Producto> productos;

	public Pallet() {
	}

	public int getIdPallet() {
		return this.idPallet;
	}

	public void setIdPallet(int idPallet) {
		this.idPallet = idPallet;
	}

	public String getPallCodigo() {
		return this.pallCodigo;
	}

	public void setPallCodigo(String pallCodigo) {
		this.pallCodigo = pallCodigo;
	}

	public String getPallDescripcion() {
		return this.pallDescripcion;
	}

	public void setPallDescripcion(String pallDescripcion) {
		this.pallDescripcion = pallDescripcion;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setPallet(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setPallet(null);

		return producto;
	}

}