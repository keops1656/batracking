package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the tipo_bateria database table.
 * 
 */
@Entity
@Table(name = "tipo_bateria")
@NamedQuery(name = "TipoBateria.findAll", query = "SELECT t FROM TipoBateria t")
public class TipoBateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipbat_codigo")
	private int tipbatCodigo;

	@Column(name = "tipbat_descripcion")
	private String tipbatDescripcion;

	@Column(name = "tipbat_nombre")
	private String tipbatNombre;

	@OneToMany(mappedBy = "tipoBateria")
	private List<Producto> productos;

	public TipoBateria() {
	}

	public int getTipbatCodigo() {
		return this.tipbatCodigo;
	}

	public void setTipbatCodigo(int tipbatCodigo) {
		this.tipbatCodigo = tipbatCodigo;
	}

	public String getTipbatDescripcion() {
		return this.tipbatDescripcion;
	}

	public void setTipbatDescripcion(String tipbatDescripcion) {
		this.tipbatDescripcion = tipbatDescripcion;
	}

	public String getTipbatNombre() {
		return this.tipbatNombre;
	}

	public void setTipbatNombre(String tipbatNombre) {
		this.tipbatNombre = tipbatNombre;
	}

	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	

}