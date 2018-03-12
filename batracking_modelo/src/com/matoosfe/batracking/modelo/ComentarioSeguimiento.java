package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the comentario_seguimiento database table.
 * 
 */
@Entity
@Table(name = "comentario_seguimiento")
@NamedQuery(name = "ComentarioSeguimiento.findAll", query = "SELECT c FROM ComentarioSeguimiento c")
public class ComentarioSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_observacion_seg")
	private int idObservacionSeg;

	@Column(name = "oseg_descripcion")
	private String osegDescripcion;

	@Transient
	private int idTmp;

	// bi-directional many-to-one association to Seguimiento
	@ManyToOne
	@JoinColumn(name = "id_seguimiento")
	private Seguimiento seguimiento;

	public ComentarioSeguimiento() {
	}

	public int getIdObservacionSeg() {
		return this.idObservacionSeg;
	}

	public void setIdObservacionSeg(int idObservacionSeg) {
		this.idObservacionSeg = idObservacionSeg;
	}

	public String getOsegDescripcion() {
		return this.osegDescripcion;
	}

	public void setOsegDescripcion(String osegDescripcion) {
		this.osegDescripcion = osegDescripcion;
	}

	/**
	 * @return the idTmp
	 */
	public int getIdTmp() {
		return idTmp;
	}

	/**
	 * @param idTmp
	 *            the idTmp to set
	 */
	public void setIdTmp(int idTmp) {
		this.idTmp = idTmp;
	}

	public Seguimiento getSeguimiento() {
		return this.seguimiento;
	}

	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}

}