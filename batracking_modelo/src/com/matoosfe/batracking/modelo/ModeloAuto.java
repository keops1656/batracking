package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the modelo_auto database table.
 * 
 */
@Entity
@Table(name="modelo_auto")
@NamedQuery(name="ModeloAuto.findAll", query="SELECT m FROM ModeloAuto m")
public class ModeloAuto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="anio")
	private int anio;

	@Column(name="descripcion")
	private String descripcion;

	@Column(name="motor")
	private String motor;

	//bi-directional many-to-one association to EspecficacionBateria
	@ManyToOne
	@JoinColumn(name="id_especificacion")
	private EspecficacionBateria especficacionBateria;

	//bi-directional many-to-one association to MarcaAuto
	@ManyToOne
	@JoinColumn(name="id_marca")
	private MarcaAuto marcaAuto;

	public ModeloAuto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMotor() {
		return this.motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public EspecficacionBateria getEspecficacionBateria() {
		return this.especficacionBateria;
	}

	public void setEspecficacionBateria(EspecficacionBateria especficacionBateria) {
		this.especficacionBateria = especficacionBateria;
	}

	public MarcaAuto getMarcaAuto() {
		return this.marcaAuto;
	}

	public void setMarcaAuto(MarcaAuto marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

}