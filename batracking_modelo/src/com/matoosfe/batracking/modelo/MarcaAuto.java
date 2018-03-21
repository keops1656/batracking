package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marca_auto database table.
 * 
 */
@Entity
@Table(name="marca_auto")
@NamedQuery(name="MarcaAuto.findAll", query="SELECT m FROM MarcaAuto m")
public class MarcaAuto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="descripcion")
	private String descripcion;

	//bi-directional many-to-one association to ModeloAuto
	@OneToMany(mappedBy="marcaAuto")
	private List<ModeloAuto> modeloAutos;

	public MarcaAuto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ModeloAuto> getModeloAutos() {
		return this.modeloAutos;
	}

	public void setModeloAutos(List<ModeloAuto> modeloAutos) {
		this.modeloAutos = modeloAutos;
	}

	public ModeloAuto addModeloAuto(ModeloAuto modeloAuto) {
		getModeloAutos().add(modeloAuto);
		modeloAuto.setMarcaAuto(this);

		return modeloAuto;
	}

	public ModeloAuto removeModeloAuto(ModeloAuto modeloAuto) {
		getModeloAutos().remove(modeloAuto);
		modeloAuto.setMarcaAuto(null);

		return modeloAuto;
	}

}