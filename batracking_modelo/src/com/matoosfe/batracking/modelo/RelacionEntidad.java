package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the relacion_entidad database table.
 * 
 */
@Entity
@Table(name="relacion_entidad")
@NamedQuery(name="RelacionEntidad.findAll", query="SELECT r FROM RelacionEntidad r")
public class RelacionEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="relent_codigo")
	private int relentCodigo;

	@Column(name="id_entidad")
	private int idEntidad;

	@Column(name="id_entidad_padre")
	private int idEntidadPadre;

	@Temporal(TemporalType.DATE)
	@Column(name="relent_fecha_asigna")
	private Date relentFechaAsigna;

	public RelacionEntidad() {
	}

	public int getRelentCodigo() {
		return this.relentCodigo;
	}

	public void setRelentCodigo(int relentCodigo) {
		this.relentCodigo = relentCodigo;
	}

	public int getIdEntidad() {
		return this.idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdEntidadPadre() {
		return this.idEntidadPadre;
	}

	public void setIdEntidadPadre(int idEntidadPadre) {
		this.idEntidadPadre = idEntidadPadre;
	}

	public Date getRelentFechaAsigna() {
		return this.relentFechaAsigna;
	}

	public void setRelentFechaAsigna(Date relentFechaAsigna) {
		this.relentFechaAsigna = relentFechaAsigna;
	}

}