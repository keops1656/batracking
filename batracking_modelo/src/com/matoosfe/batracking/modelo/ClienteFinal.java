package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the cliente_final database table.
 * 
 */
@Entity
@Table(name = "cliente_final")
@NamedQuery(name = "ClienteFinal.findAll", query = "SELECT c FROM ClienteFinal c")
public class ClienteFinal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int idCliente;

	@Column(name = "cli_vehiculo_marca")
	private String cliVehiculoMarca;

	@Column(name = "cli_vehiculo_modelo")
	private String cliVehiculoModelo;

	@Column(name = "cli_vehiculo_placa")
	private String cliVehiculoPlaca;

	@Column(name = "cli_vehiculo_color")
	private String cliVehiculoColor;

	@Column(name = "cli_observacion")
	private String cliObservacion;

	@Column(name = "cli_nombres")
	private String cliNombres;

	@Column(name = "cli_apellidos")
	private String cliApellidos;

	@Column(name = "cli_telefono")
	private String cliTelefono;

	@Column(name = "cli_email")
	private String cliEmail;

	// bi-directional many-to-one association to Seguimiento
	@ManyToOne
	@JoinColumn(name = "id_seguimiento")
	private Seguimiento seguimiento;

	public ClienteFinal() {
	}

	/**
	 * @return the idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the cliVehiculoMarca
	 */
	public String getCliVehiculoMarca() {
		return cliVehiculoMarca;
	}

	/**
	 * @param cliVehiculoMarca
	 *            the cliVehiculoMarca to set
	 */
	public void setCliVehiculoMarca(String cliVehiculoMarca) {
		this.cliVehiculoMarca = cliVehiculoMarca;
	}

	/**
	 * @return the cliVehiculoModelo
	 */
	public String getCliVehiculoModelo() {
		return cliVehiculoModelo;
	}

	/**
	 * @param cliVehiculoModelo
	 *            the cliVehiculoModelo to set
	 */
	public void setCliVehiculoModelo(String cliVehiculoModelo) {
		this.cliVehiculoModelo = cliVehiculoModelo;
	}

	/**
	 * @return the cliVehiculoPlaca
	 */
	public String getCliVehiculoPlaca() {
		return cliVehiculoPlaca;
	}

	/**
	 * @param cliVehiculoPlaca
	 *            the cliVehiculoPlaca to set
	 */
	public void setCliVehiculoPlaca(String cliVehiculoPlaca) {
		this.cliVehiculoPlaca = cliVehiculoPlaca;
	}

	/**
	 * @return the cliVehiculoColor
	 */
	public String getCliVehiculoColor() {
		return cliVehiculoColor;
	}

	/**
	 * @param cliVehiculoColor
	 *            the cliVehiculoColor to set
	 */
	public void setCliVehiculoColor(String cliVehiculoColor) {
		this.cliVehiculoColor = cliVehiculoColor;
	}

	/**
	 * @return the cliObservacion
	 */
	public String getCliObservacion() {
		return cliObservacion;
	}

	/**
	 * @param cliObservacion
	 *            the cliObservacion to set
	 */
	public void setCliObservacion(String cliObservacion) {
		this.cliObservacion = cliObservacion;
	}

	/**
	 * @return the cliNombres
	 */
	public String getCliNombres() {
		return cliNombres;
	}

	/**
	 * @param cliNombres
	 *            the cliNombres to set
	 */
	public void setCliNombres(String cliNombres) {
		this.cliNombres = cliNombres;
	}

	/**
	 * @return the cliApellidos
	 */
	public String getCliApellidos() {
		return cliApellidos;
	}

	/**
	 * @param cliApellidos
	 *            the cliApellidos to set
	 */
	public void setCliApellidos(String cliApellidos) {
		this.cliApellidos = cliApellidos;
	}

	/**
	 * @return the cliTelefono
	 */
	public String getCliTelefono() {
		return cliTelefono;
	}

	/**
	 * @param cliTelefono
	 *            the cliTelefono to set
	 */
	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	/**
	 * @return the cliEmail
	 */
	public String getCliEmail() {
		return cliEmail;
	}

	/**
	 * @param cliEmail
	 *            the cliEmail to set
	 */
	public void setCliEmail(String cliEmail) {
		this.cliEmail = cliEmail;
	}

	/**
	 * @return the seguimiento
	 */
	public Seguimiento getSeguimiento() {
		return seguimiento;
	}

	/**
	 * @param seguimiento
	 *            the seguimiento to set
	 */
	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}

}