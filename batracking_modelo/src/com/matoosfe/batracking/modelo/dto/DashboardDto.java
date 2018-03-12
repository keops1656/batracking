package com.matoosfe.batracking.modelo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el objeto reporte
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 20 ago. 2017-
 *         16:33:51<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
public class DashboardDto {
	private String titulo;
	private String etiqueta;
	private int valor;
	private String color;
	private List<ReporteDto> listaProductosReporte;

	public DashboardDto() {
		this.listaProductosReporte = new ArrayList<>();
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * @param etiqueta
	 *            the etiqueta to set
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
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
	 * @return the listaProductosReporte
	 */
	public List<ReporteDto> getListaProductosReporte() {
		return listaProductosReporte;
	}

	/**
	 * @param listaProductosReporte
	 *            the listaProductosReporte to set
	 */
	public void setListaProductosReporte(List<ReporteDto> listaProductosReporte) {
		this.listaProductosReporte = listaProductosReporte;
	}

}
