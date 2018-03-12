package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa la tabla Producto
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 17 ago. 2017-
 *         23:57:44<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Entity
@Table(name = "producto")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int idProducto;

	@Column(name = "prod_codigo")
	private String prodCodigo;

	@Column(name = "prod_especificaciones")
	private String prodEspecificaciones;

	@Column(name = "prod_estado_bateria")
	private String prodEstadoBateria;

	@Temporal(TemporalType.DATE)
	@Column(name = "prod_fecha_mantenimiento")
	private Date prodFechaMantenimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "prod_fecha_produccion")
	private Date prodFechaProduccion;

	@Temporal(TemporalType.DATE)
	@Column(name = "prod_fecha_repone")
	private Date prodFechaRepone;

	@Temporal(TemporalType.DATE)
	@Column(name = "prod_fecha_venta")
	private Date prodFechaVenta;

	@Column(name = "prod_contador_refrescar")
	private int prodContadorRefrescar;

	// bi-directional many-to-one association to Pallet
	@ManyToOne
	@JoinColumn(name = "id_pallet")
	private Pallet pallet;

	// bi-directional many-to-one association to Fabricante
	@ManyToOne
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	@ManyToOne
	@JoinColumn(name = "par_codigo")
	private Parametro parametro;

	// bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
	private List<Seguimiento> seguimientos;

	@ManyToOne
	@JoinColumn(name = "tipbat_codigo")
	private TipoBateria tipoBateria;;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getProdCodigo() {
		return this.prodCodigo;
	}

	public void setProdCodigo(String prodCodigo) {
		this.prodCodigo = prodCodigo;
	}

	public String getProdEspecificaciones() {
		return this.prodEspecificaciones;
	}

	public void setProdEspecificaciones(String prodEspecificaciones) {
		this.prodEspecificaciones = prodEspecificaciones;
	}

	public String getProdEstadoBateria() {
		return this.prodEstadoBateria;
	}

	public void setProdEstadoBateria(String prodEstadoBateria) {
		this.prodEstadoBateria = prodEstadoBateria;
	}

	public Date getProdFechaMantenimiento() {
		return this.prodFechaMantenimiento;
	}

	public void setProdFechaMantenimiento(Date prodFechaMantenimiento) {
		this.prodFechaMantenimiento = prodFechaMantenimiento;
	}

	public Date getProdFechaProduccion() {
		return this.prodFechaProduccion;
	}

	public void setProdFechaProduccion(Date prodFechaProduccion) {
		this.prodFechaProduccion = prodFechaProduccion;
	}

	public Date getProdFechaRepone() {
		return this.prodFechaRepone;
	}

	public void setProdFechaRepone(Date prodFechaRepone) {
		this.prodFechaRepone = prodFechaRepone;
	}

	public Date getProdFechaVenta() {
		return this.prodFechaVenta;
	}

	public void setProdFechaVenta(Date prodFechaVenta) {
		this.prodFechaVenta = prodFechaVenta;
	}

	/**
	 * @return the prodContadorRefrescar
	 */
	public int getProdContadorRefrescar() {
		return prodContadorRefrescar;
	}

	/**
	 * @param prodContadorRefrescar
	 *            the prodContadorRefrescar to set
	 */
	public void setProdContadorRefrescar(int prodContadorRefrescar) {
		this.prodContadorRefrescar = prodContadorRefrescar;
	}

	public Pallet getPallet() {
		return this.pallet;
	}

	public void setPallet(Pallet pallet) {
		this.pallet = pallet;
	}

	/**
	 * @return the entidad
	 */
	public Entidad getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad
	 *            the entidad to set
	 */
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the parametro
	 */
	public Parametro getParametro() {
		return parametro;
	}

	/**
	 * @param parametro
	 *            the parametro to set
	 */
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	/**
	 * @return the tipoBateria
	 */
	public TipoBateria getTipoBateria() {
		return tipoBateria;
	}

	/**
	 * @param tipoBateria
	 *            the tipoBateria to set
	 */
	public void setTipoBateria(TipoBateria tipoBateria) {
		this.tipoBateria = tipoBateria;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setProducto(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setProducto(null);

		return seguimiento;
	}

}