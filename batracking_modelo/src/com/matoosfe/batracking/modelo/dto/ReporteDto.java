package com.matoosfe.batracking.modelo.dto;

import com.matoosfe.batracking.modelo.ClienteFinal;
import com.matoosfe.batracking.modelo.Seguimiento;

/**
 * Clase que representa el objeto reporte
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 20 ago. 2017-
 *         16:33:51<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
public class ReporteDto {
	private ClienteFinal cliente;
	private Seguimiento seguimiento;
	private String color;
	private boolean vendidaClienteFinal;
	private boolean garantia;
	private int diasRestantesVU;
	private int valorRojo;
	private int valorAmarillo;
	private int valorVerde;

	public ReporteDto() {

	}

	/**
	 * @return the cliente
	 */
	public ClienteFinal getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(ClienteFinal cliente) {
		this.cliente = cliente;
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

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the vendidaClienteFinal
	 */
	public boolean isVendidaClienteFinal() {
		return vendidaClienteFinal;
	}

	/**
	 * @param vendidaClienteFinal
	 *            the vendidaClienteFinal to set
	 */
	public void setVendidaClienteFinal(boolean vendidaClienteFinal) {
		this.vendidaClienteFinal = vendidaClienteFinal;
	}

	/**
	 * @return the garantia
	 */
	public boolean isGarantia() {
		return garantia;
	}

	/**
	 * @param garantia
	 *            the garantia to set
	 */
	public void setGarantia(boolean garantia) {
		this.garantia = garantia;
	}

	/**
	 * @return the diasRestantesVU
	 */
	public int getDiasRestantesVU() {
		return diasRestantesVU;
	}

	/**
	 * @param diasRestantesVU
	 *            the diasRestantesVU to set
	 */
	public void setDiasRestantesVU(int diasRestantesVU) {
		this.diasRestantesVU = diasRestantesVU;
	}

	/**
	 * @return the valorRojo
	 */
	public int getValorRojo() {
		return valorRojo;
	}

	/**
	 * @param valorRojo
	 *            the valorRojo to set
	 */
	public void setValorRojo(int valorRojo) {
		this.valorRojo = valorRojo;
	}

	/**
	 * @return the valorAmarillo
	 */
	public int getValorAmarillo() {
		return valorAmarillo;
	}

	/**
	 * @param valorAmarillo
	 *            the valorAmarillo to set
	 */
	public void setValorAmarillo(int valorAmarillo) {
		this.valorAmarillo = valorAmarillo;
	}

	/**
	 * @return the valorVerde
	 */
	public int getValorVerde() {
		return valorVerde;
	}

	/**
	 * @param valorVerde
	 *            the valorVerde to set
	 */
	public void setValorVerde(int valorVerde) {
		this.valorVerde = valorVerde;
	}

}
