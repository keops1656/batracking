package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especficacion_bateria database table.
 * 
 */
@Entity
@Table(name="especficacion_bateria")
@NamedQuery(name="EspecficacionBateria.findAll", query="SELECT e FROM EspecficacionBateria e")
public class EspecficacionBateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="codEspecificacion")
	private String codEspecificacion;

	//bi-directional many-to-one association to ModeloAuto
	@OneToMany(mappedBy="especficacionBateria")
	private List<ModeloAuto> modeloAutos;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="especficacionBateria")
	private List<Producto> productos;

	public EspecficacionBateria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodEspecificacion() {
		return this.codEspecificacion;
	}

	public void setCodEspecificacion(String codEspecificacion) {
		this.codEspecificacion = codEspecificacion;
	}

	public List<ModeloAuto> getModeloAutos() {
		return this.modeloAutos;
	}

	public void setModeloAutos(List<ModeloAuto> modeloAutos) {
		this.modeloAutos = modeloAutos;
	}

	public ModeloAuto addModeloAuto(ModeloAuto modeloAuto) {
		getModeloAutos().add(modeloAuto);
		modeloAuto.setEspecficacionBateria(this);

		return modeloAuto;
	}

	public ModeloAuto removeModeloAuto(ModeloAuto modeloAuto) {
		getModeloAutos().remove(modeloAuto);
		modeloAuto.setEspecficacionBateria(null);

		return modeloAuto;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setEspecficacionBateria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setEspecficacionBateria(null);

		return producto;
	}

}