package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * Clase que representa la tabla Tipo Entidad
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 
 * 17 ago. 2017- 22:54:00<br>
 * <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking" target="_top">Soporte</a><br>
 * <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Entity
@Table(name = "tipo_entidad")
@NamedQuery(name = "TipoEntidad.findAll", query = "SELECT t FROM TipoEntidad t")
public class TipoEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_entidad")
	private int idTipoEntidad;

	@Column(name = "tipent_nombre")
	private String tipentNombre;

	@Column(name = "tipent_orden")
	private Integer tipentOrden;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "tipoEntidad")
	private List<Entidad> entidades;

	public TipoEntidad() {
	}

	public int getIdTipoEntidad() {
		return this.idTipoEntidad;
	}

	public void setIdTipoEntidad(int idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}

	/**
	 * @return the tipentNombre
	 */
	public String getTipentNombre() {
		return tipentNombre;
	}

	/**
	 * @param tipentNombre
	 *            the tipentNombre to set
	 */
	public void setTipentNombre(String tipentNombre) {
		this.tipentNombre = tipentNombre;
	}

	/**
	 * @return the tipentOrden
	 */
	public Integer getTipentOrden() {
		return tipentOrden;
	}

	/**
	 * @param tipentOrden
	 *            the tipentOrden to set
	 */
	public void setTipentOrden(Integer tipentOrden) {
		this.tipentOrden = tipentOrden;
	}

	/**
	 * @return the entidades
	 */
	public List<Entidad> getEntidades() {
		return entidades;
	}

	/**
	 * @param entidades
	 *            the entidades to set
	 */
	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}

}