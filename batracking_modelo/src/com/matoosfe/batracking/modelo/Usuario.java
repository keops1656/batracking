package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa la tabla Usuario
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 17 ago. 2017-
 *         22:54:44<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "usu_activo")
	private int usuActivo;

	@Column(name = "usu_apellido")
	private String usuApellido;

	@Column(name = "usu_correo")
	private String usuCorreo;

	@Temporal(TemporalType.DATE)
	@Column(name = "usu_fecha_creacion")
	private Date usuFechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "usu_fecha_modificacion")
	private Date usuFechaModificacion;

	@Column(name = "usu_login")
	private String usuLogin;

	@Column(name = "usu_nombre")
	private String usuNombre;

	@Column(name = "usu_password")
	private String usuPassword;

	@Column(name = "usu_rol")
	private String usuRol;

	@OneToMany(mappedBy = "usuario")
	private List<Seguimiento> seguimientos;

	@ManyToOne
	@JoinColumn(name = "id_entidad")
	private Entidad entidad;

	public Usuario() {
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the usuActivo
	 */
	public int getUsuActivo() {
		return usuActivo;
	}

	/**
	 * @param usuActivo
	 *            the usuActivo to set
	 */
	public void setUsuActivo(int usuActivo) {
		this.usuActivo = usuActivo;
	}

	/**
	 * @return the usuApellido
	 */
	public String getUsuApellido() {
		return usuApellido;
	}

	/**
	 * @param usuApellido
	 *            the usuApellido to set
	 */
	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	/**
	 * @return the usuCorreo
	 */
	public String getUsuCorreo() {
		return usuCorreo;
	}

	/**
	 * @param usuCorreo
	 *            the usuCorreo to set
	 */
	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}

	/**
	 * @return the usuFechaCreacion
	 */
	public Date getUsuFechaCreacion() {
		return usuFechaCreacion;
	}

	/**
	 * @param usuFechaCreacion
	 *            the usuFechaCreacion to set
	 */
	public void setUsuFechaCreacion(Date usuFechaCreacion) {
		this.usuFechaCreacion = usuFechaCreacion;
	}

	/**
	 * @return the usuFechaModificacion
	 */
	public Date getUsuFechaModificacion() {
		return usuFechaModificacion;
	}

	/**
	 * @param usuFechaModificacion
	 *            the usuFechaModificacion to set
	 */
	public void setUsuFechaModificacion(Date usuFechaModificacion) {
		this.usuFechaModificacion = usuFechaModificacion;
	}

	/**
	 * @return the usuLogin
	 */
	public String getUsuLogin() {
		return usuLogin;
	}

	/**
	 * @param usuLogin
	 *            the usuLogin to set
	 */
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	/**
	 * @return the usuNombre
	 */
	public String getUsuNombre() {
		return usuNombre;
	}

	/**
	 * @param usuNombre
	 *            the usuNombre to set
	 */
	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	/**
	 * @return the usuPassword
	 */
	public String getUsuPassword() {
		return usuPassword;
	}

	/**
	 * @param usuPassword
	 *            the usuPassword to set
	 */
	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

	/**
	 * @return the usuRol
	 */
	public String getUsuRol() {
		return usuRol;
	}

	/**
	 * @param usuRol
	 *            the usuRol to set
	 */
	public void setUsuRol(String usuRol) {
		this.usuRol = usuRol;
	}

	/**
	 * @return the seguimientos
	 */
	public List<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	/**
	 * @param seguimientos
	 *            the seguimientos to set
	 */
	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
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

}