package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the seguimiento database table.
 * 
 */
@Entity
@Table(name = "seguimiento")
@NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s")
public class Seguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_seguimiento")
	private int idSeguimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "seg_fecha")
	private Date segFecha;

	@Column(name = "seg_latitud")
	private String segLatitud;

	@Column(name = "seg_longitud")
	private String segLongitud;

	@Column(name = "seg_lugar")
	private String segLugar;

	@Column(name = "seg_vida_util")
	private Integer segVidaUtil;

	@Column(name = "seg_mantenimiento")
	private Integer segMantenimiento;

	@Column(name = "seg_estado")
	private String segEstado;

	@Column(name = "seg_actual")
	private int segActual;

	@Column(name = "seg_tiempo_refrescar")
	private int segTiempoRefrescar;

	@Temporal(TemporalType.DATE)
	@Column(name = "seg_fecha_refrescar")
	private Date segFechaRefrescar;

	// bi-directional many-to-one association to ClienteFinal
	@OneToMany(mappedBy = "seguimiento")
	private List<ClienteFinal> clienteFinals;

	// bi-directional many-to-one association to ComentarioSeguimiento
	@OneToMany(mappedBy = "seguimiento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ComentarioSeguimiento> comentarioSeguimientos;

	// bi-directional many-to-one association to FotografiasSeguimiento
	@OneToMany(mappedBy = "seguimiento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FotografiasSeguimiento> fotografiasSeguimientos;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Seguimiento() {
	}

	/**
	 * @return the idSeguimiento
	 */
	public int getIdSeguimiento() {
		return idSeguimiento;
	}

	/**
	 * @param idSeguimiento
	 *            the idSeguimiento to set
	 */
	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	/**
	 * @return the segFecha
	 */
	public Date getSegFecha() {
		return segFecha;
	}

	/**
	 * @param segFecha
	 *            the segFecha to set
	 */
	public void setSegFecha(Date segFecha) {
		this.segFecha = segFecha;
	}

	/**
	 * @return the segLatitud
	 */
	public String getSegLatitud() {
		return segLatitud;
	}

	/**
	 * @param segLatitud
	 *            the segLatitud to set
	 */
	public void setSegLatitud(String segLatitud) {
		this.segLatitud = segLatitud;
	}

	/**
	 * @return the segLongitud
	 */
	public String getSegLongitud() {
		return segLongitud;
	}

	/**
	 * @param segLongitud
	 *            the segLongitud to set
	 */
	public void setSegLongitud(String segLongitud) {
		this.segLongitud = segLongitud;
	}

	/**
	 * @return the segLugar
	 */
	public String getSegLugar() {
		return segLugar;
	}

	/**
	 * @param segLugar
	 *            the segLugar to set
	 */
	public void setSegLugar(String segLugar) {
		this.segLugar = segLugar;
	}

	/**
	 * @return the segVidaUtil
	 */
	public Integer getSegVidaUtil() {
		return segVidaUtil;
	}

	/**
	 * @param segVidaUtil
	 *            the segVidaUtil to set
	 */
	public void setSegVidaUtil(Integer segVidaUtil) {
		this.segVidaUtil = segVidaUtil;
	}

	/**
	 * @return the segMantenimiento
	 */
	public Integer getSegMantenimiento() {
		return segMantenimiento;
	}

	/**
	 * @param segMantenimiento
	 *            the segMantenimiento to set
	 */
	public void setSegMantenimiento(Integer segMantenimiento) {
		this.segMantenimiento = segMantenimiento;
	}

	/**
	 * @return the segEstado
	 */
	public String getSegEstado() {
		return segEstado;
	}

	/**
	 * @param segEstado
	 *            the segEstado to set
	 */
	public void setSegEstado(String segEstado) {
		this.segEstado = segEstado;
	}

	/**
	 * @return the segActual
	 */
	public int getSegActual() {
		return segActual;
	}

	/**
	 * @param segActual
	 *            the segActual to set
	 */
	public void setSegActual(int segActual) {
		this.segActual = segActual;
	}

	/**
	 * @return the segTiempoRefrescar
	 */
	public int getSegTiempoRefrescar() {
		return segTiempoRefrescar;
	}

	/**
	 * @param segTiempoRefrescar
	 *            the segTiempoRefrescar to set
	 */
	public void setSegTiempoRefrescar(int segTiempoRefrescar) {
		this.segTiempoRefrescar = segTiempoRefrescar;
	}

	/**
	 * @return the segFechaRefrescar
	 */
	public Date getSegFechaRefrescar() {
		return segFechaRefrescar;
	}

	/**
	 * @param segFechaRefrescar
	 *            the segFechaRefrescar to set
	 */
	public void setSegFechaRefrescar(Date segFechaRefrescar) {
		this.segFechaRefrescar = segFechaRefrescar;
	}

	/**
	 * @return the clienteFinals
	 */
	public List<ClienteFinal> getClienteFinals() {
		return clienteFinals;
	}

	/**
	 * @param clienteFinals
	 *            the clienteFinals to set
	 */
	public void setClienteFinals(List<ClienteFinal> clienteFinals) {
		this.clienteFinals = clienteFinals;
	}

	/**
	 * @return the comentarioSeguimientos
	 */
	public List<ComentarioSeguimiento> getComentarioSeguimientos() {
		return comentarioSeguimientos;
	}

	/**
	 * @param comentarioSeguimientos
	 *            the comentarioSeguimientos to set
	 */
	public void setComentarioSeguimientos(List<ComentarioSeguimiento> comentarioSeguimientos) {
		this.comentarioSeguimientos = comentarioSeguimientos;
	}

	/**
	 * @return the fotografiasSeguimientos
	 */
	public List<FotografiasSeguimiento> getFotografiasSeguimientos() {
		return fotografiasSeguimientos;
	}

	/**
	 * @param fotografiasSeguimientos
	 *            the fotografiasSeguimientos to set
	 */
	public void setFotografiasSeguimientos(List<FotografiasSeguimiento> fotografiasSeguimientos) {
		this.fotografiasSeguimientos = fotografiasSeguimientos;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}