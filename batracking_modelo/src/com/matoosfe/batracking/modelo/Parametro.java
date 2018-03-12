package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@Table(name = "parametro")
@NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "par_codigo")
	private int parCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "par_fecha")
	private Date parFecha;

	@Column(name = "par_mantenimiento")
	private Integer parMantenimiento;

	@Column(name = "par_refrescamiento")
	private Integer parRefrescamiento;

	@Column(name = "par_vida_util")
	private Integer parVidaUtil;

	@OneToMany(mappedBy = "parametro")
	private List<Producto> producto;

	public Parametro() {
	}

	public int getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(int parCodigo) {
		this.parCodigo = parCodigo;
	}

	public Date getParFecha() {
		return this.parFecha;
	}

	public void setParFecha(Date parFecha) {
		this.parFecha = parFecha;
	}

	public Integer getParMantenimiento() {
		return this.parMantenimiento;
	}

	public void setParMantenimiento(Integer parMantenimiento) {
		this.parMantenimiento = parMantenimiento;
	}

	/**
	 * @return the parRefrescamiento
	 */
	public Integer getParRefrescamiento() {
		return parRefrescamiento;
	}

	/**
	 * @param parRefrescamiento
	 *            the parRefrescamiento to set
	 */
	public void setParRefrescamiento(Integer parRefrescamiento) {
		this.parRefrescamiento = parRefrescamiento;
	}

	public Integer getParVidaUtil() {
		return this.parVidaUtil;
	}

	public void setParVidaUtil(Integer parVidaUtil) {
		this.parVidaUtil = parVidaUtil;
	}

	/**
	 * @return the producto
	 */
	public List<Producto> getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Vida útil:" + parMantenimiento + " - Mantenimiento:" + parMantenimiento;
	}

}